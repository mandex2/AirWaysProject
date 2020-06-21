package com.dursun.airways.domain.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dursun.airways.common.enums.ResponseStatus;
import com.dursun.airways.common.exception.ProcessException;
import com.dursun.airways.common.utils.DateUtils;
import com.dursun.airways.common.utils.DateUtils.DateFormatTag;
import com.dursun.airways.dao.mapper.FlightDefinitionMapper;
import com.dursun.airways.dao.repository.FlightDefinitionRepository;
import com.dursun.airways.domain.dto.AirWayCompanyDTO;
import com.dursun.airways.domain.dto.FlightDefinitionDTO;
import com.dursun.airways.domain.dto.FlightDefinitionDetailsDTO;
import com.dursun.airways.domain.dto.FlightDefinitionsWithDetailsDTO;
import com.dursun.airways.domain.dto.FlightRouteDTO;
import com.dursun.airways.web.request.RequestCreateFlightDefinition;
import com.dursun.airways.web.request.RequestUpdateFlightQuota;
import com.dursun.airways.web.response.ResponseResult;

@Service
public class FlightDefinitionServiceImpl implements FlightDefinitionService {

	@Autowired
	private FlightDefinitionRepository repo;

	@Autowired
	FlightDefinitionMapper mapper;

	public FlightDefinitionServiceImpl(FlightDefinitionMapper mapper, FlightDefinitionRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Autowired
	FlightRouteService routeService;

	@Autowired
	AirWayCompanyService companyService;

	@Autowired
	FlightDefinitionDetailsService defDetailsService;

	@Override
	public List<FlightDefinitionDTO> getAllFlightDefinition() {
		return mapper.toFlightDefinitionDTOList(repo.findAll());
	}

	@Override
	public FlightDefinitionDTO createFlightDefinition(RequestCreateFlightDefinition dto) {
		FlightDefinitionDTO defDTO = new FlightDefinitionDTO();
		ResponseResult result = null;
		if (dto.getAmount() != null && dto.getFlightDate() != null && dto.getQuota() != null
				&& StringUtils.hasText(dto.getAirPortFrom()) && StringUtils.hasText(dto.getAirPortTo())
				&& StringUtils.hasText(dto.getCompanyName())) {
			FlightRouteDTO routeDTO = routeService.recordControl(dto.getAirPortFrom(), dto.getAirPortTo());
			dto.setFlightDate(
					DateUtils.stringToDate(DateUtils.getCurrentDate(dto.getFlightDate()), DateFormatTag.DATE_FORMAT_1));
			if (routeDTO == null) {// rota kontrolu
				result = ProcessException.decisionResultForException(ProcessException.UNDEFINED_ROUTE);
			} else {
				AirWayCompanyDTO companyDTO = companyService.recordControl(dto.getCompanyName());
				if (companyDTO == null) {// havayolu firmasi kontrolu
					result = ProcessException.decisionResultForException(ProcessException.UNDEFINED_AIRWAY_COMPANY);
				} else {
					// Tanim detay kontrolu eger kayit varsa mevcut kayit eklenemez hatasi
					// firlatilmali
					if (defDetailsService.recordControl(dto.getAmount(), dto.getQuota(), dto.getFlightDate())) {
						result = ProcessException.decisionResultForException(ProcessException.RECORD_NOT_SAVE);
					} else {// tum kontrollerden gecti kayit eklenmeli
						FlightDefinitionDetailsDTO detailDTO = new FlightDefinitionDetailsDTO();
						detailDTO.setAmount(dto.getAmount());
						detailDTO.setFlightDate(dto.getFlightDate());
						detailDTO.setQuota(dto.getQuota());
						detailDTO = defDetailsService.createFlightDefinitionDetails(detailDTO);
						if (detailDTO != null) {
							defDTO.setCompanyID(companyDTO.getId());
							defDTO.setDetailsID(detailDTO.getId());
							defDTO.setRouteID(routeDTO.getId());
							defDTO = mapper.toFlightDefinitionDTO(repo.save(mapper.toFlightDefinition(defDTO)));
						}
					}
				}
			}
		}else {
			result = ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
		if (result != null) {
			defDTO.setErrorID(result.getErrorID());
			defDTO.setErrorDescription(result.getErrorDescription());
			defDTO.setStatus(ResponseStatus.FAILURE);
		}
		return defDTO;
	}

	@Override
	public ResponseResult updateFlightQuota(RequestUpdateFlightQuota dto) {
		if (dto.getFlightID() != null) {
			FlightDefinitionDTO defDTO = mapper.toFlightDefinitionDTO(repo.findByFlightID(dto.getFlightID()));
			if (defDTO == null) {
				return ProcessException.decisionResultForException(ProcessException.NO_DATA_FOUND);
			} else {
				FlightDefinitionDetailsDTO detailDTO = defDetailsService
						.getFlightDefinitionDetailsByID(defDTO.getDetailsID());
				if (detailDTO != null) {
					Integer detaildQuota = detailDTO.getQuota();
					Integer newQuota = dto.getQuota();
					// yeni gelen kota mevcutun % kaci hesaplandi eger - ise azalmis demektir
					// + ise ve %10un katlarinda tutarida %10un katinda arttiriyoruz
					Integer perc = ((newQuota - detaildQuota) * 100 / detaildQuota);
					if (perc > 0) {
						// %lik degerin 10 ile bolumundeki bolum kismi kadar bilet tutari %lik
						// arttirilacak
						Double section = Double.valueOf(perc / 10 * 10) / 100;
						BigDecimal decimalSection = new BigDecimal(Double.toString(section));
						BigDecimal amount = detailDTO.getAmount();
						amount = amount.add(amount.multiply(decimalSection));
						amount.setScale(2, RoundingMode.HALF_UP);
						detailDTO.setAmount(amount);
					}
					detailDTO.setQuota(dto.getQuota());
					defDetailsService.createFlightDefinitionDetails(detailDTO);
				} else {
					return ProcessException.decisionResultForException(ProcessException.NO_DATA_FOUND);
				}
			}
		} else {
			return ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
		return new ResponseResult();
	}

	@Override
	public FlightDefinitionsWithDetailsDTO findFlightByID(Long flightID) {
		FlightDefinitionDTO masterDTO = mapper.toFlightDefinitionDTO(repo.findByFlightID(flightID));
		if(masterDTO!=null) {
			FlightDefinitionDetailsDTO detailDTO = defDetailsService
					.getFlightDefinitionDetailsByID(masterDTO.getDetailsID());
			if(detailDTO!=null) {
				FlightDefinitionsWithDetailsDTO dto = new FlightDefinitionsWithDetailsDTO();
				dto.setDetailsDTO(detailDTO);
				dto.setMasterDTO(masterDTO);
				return dto;
			}
		}
		return null ;
	}
}

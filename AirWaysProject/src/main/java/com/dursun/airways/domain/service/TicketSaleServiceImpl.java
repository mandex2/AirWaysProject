package com.dursun.airways.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dursun.airways.common.enums.ResponseStatus;
import com.dursun.airways.common.enums.StatusEnum;
import com.dursun.airways.common.exception.ProcessException;
import com.dursun.airways.dao.entity.TicketSale;
import com.dursun.airways.dao.mapper.TicketSaleMapper;
import com.dursun.airways.dao.repository.TicketSaleRepository;
import com.dursun.airways.domain.dto.FlightDefinitionsWithDetailsDTO;
import com.dursun.airways.domain.dto.TicketSaleDTO;
import com.dursun.airways.web.request.RequestTicketSale;
import com.dursun.airways.web.request.RequestTicketSaleCancel;
import com.dursun.airways.web.response.ResponseResult;

@Service
public class TicketSaleServiceImpl implements TicketSaleService {

	@Autowired
	private TicketSaleRepository repo;

	@Autowired
	TicketSaleMapper mapper;

	@Autowired
	FlightDefinitionService definitionService;

	public TicketSaleServiceImpl(TicketSaleMapper mapper, TicketSaleRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Override
	public List<TicketSaleDTO> getAllTicketSale() {
		return mapper.toTicketSaleDTOList(repo.findAll());
	}

	@Override
	public TicketSaleDTO createTicketSale(RequestTicketSale dto) {

		ResponseResult result = null;
		TicketSaleDTO saleDTO = new TicketSaleDTO();
		if (dto.getFlightID() != null && dto.getAmount() != null && dto.getCartNumber() != null
				&& StringUtils.hasText(dto.getName()) && StringUtils.hasText(dto.getSurName())) {
			FlightDefinitionsWithDetailsDTO defDTO = definitionService.findFlightByID(dto.getFlightID());
			if (defDTO != null && defDTO.getDetailsDTO() != null && defDTO.getMasterDTO() != null) {
				// ucus kaydi bulunduktan sonra tarih kontrolu yapilarak bilet satisi yapilamaz
				if ((new Date()).before(defDTO.getDetailsDTO().getFlightDate())) {
					if (dto.getAmount().compareTo(defDTO.getDetailsDTO().getAmount()) >= 0) {
						// eger input olarak girilen tutarla ucusa tanimli tutar buyuk ve esit ise satis
						// gerceklesir
						String cardNumber = com.dursun.airways.common.utils.StringUtils
								.creditCardControl(dto.getCartNumber());
						if (StringUtils.hasText(cardNumber) && cardNumber.length() == 16) {
							cardNumber = com.dursun.airways.common.utils.StringUtils.creditCardMask(cardNumber);
							// tumkontrollerden gecti ACTIVE statude kayit atilmali
							dto.setAmount(defDTO.getDetailsDTO().getAmount());// tanimdaki tutar alinmali
							saleDTO = saveTicketSale(dto, cardNumber);
						} else {
							result = ProcessException.decisionResultForException(ProcessException.INVALID_CREDIT_CARD);
						}
					} else {
						result = ProcessException.decisionResultForException(ProcessException.INSUFFICIENT_BALANCE);
					}
				} else {
					result = ProcessException.decisionResultForException(ProcessException.FLIGHT_DATE_PASS);
				}
			} else {
				result = ProcessException.decisionResultForException(ProcessException.FLIGHT_NO_FOUND);
			}
		} else {
			result = ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
		if (result != null) {
			saleDTO.setErrorID(result.getErrorID());
			saleDTO.setErrorDescription(result.getErrorDescription());
			saleDTO.setStatus(ResponseStatus.FAILURE);
		}
		return saleDTO;
	}

	private TicketSaleDTO saveTicketSale(RequestTicketSale dto, String cardNumber) {
		TicketSaleDTO createDTO = new TicketSaleDTO();
		createDTO.setAmount(dto.getAmount());
		createDTO.setCartNumber(cardNumber);
		createDTO.setFlightID(dto.getFlightID());
		createDTO.setName(dto.getName());
		createDTO.setSurname(dto.getSurName());
		createDTO.setStatu(StatusEnum.ACTIVE.getValue());
		return mapper.toTicketSaleDTO(repo.save(mapper.toTicketSale(createDTO)));
	}

	@Override
	public TicketSaleDTO createTicketSaleCancel(RequestTicketSaleCancel dto) {
		ResponseResult result = null;
		TicketSaleDTO saleDTO = new TicketSaleDTO();
		if (dto.getTicketID() != null) {
			Optional<TicketSale> ticketDTO = repo.findById(dto.getTicketID());
			if (ticketDTO != null && ticketDTO.get() != null) {
				saleDTO = mapper.toTicketSaleDTO(ticketDTO.get());
				FlightDefinitionsWithDetailsDTO defDTO = definitionService.findFlightByID(saleDTO.getFlightID());
				if (defDTO != null && defDTO.getDetailsDTO() != null && defDTO.getMasterDTO() != null) {
					// Bilet bulunduktan sonra iptal edilecek zaman (suan) eger ucusun saat buyuk ve
					// esit ise iptal edilemez
					if ((new Date()).before(defDTO.getDetailsDTO().getFlightDate())) {
						saleDTO.setStatu(StatusEnum.PASSIVE.getValue());
						repo.save(mapper.toTicketSale(saleDTO));// STATU P ye CEKILDI IPTAL EDILDI
					} else {
						result = ProcessException.decisionResultForException(ProcessException.FLIGHT_DATE_PASS);
					}
				} else {
					result = ProcessException.decisionResultForException(ProcessException.FLIGHT_NO_FOUND);
				}
			} else {
				result = ProcessException.decisionResultForException(ProcessException.NO_DATA_FOUND);
			}
		} else {
			result = ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
		if (result != null) {
			saleDTO.setErrorID(result.getErrorID());
			saleDTO.setErrorDescription(result.getErrorDescription());
			saleDTO.setStatus(ResponseStatus.FAILURE);
		}
		return saleDTO;
	}

	@Override
	public TicketSaleDTO recordControl(Long ticketId) {
		if (ticketId != null) {
			Optional<TicketSale> ticketDTO = repo.findById(ticketId);
			if (ticketDTO != null && ticketDTO.get() != null) {
				return mapper.toTicketSaleDTO(ticketDTO.get());
			}else {
				return null;
			}
		} else {
			return null;
		}
	}
}
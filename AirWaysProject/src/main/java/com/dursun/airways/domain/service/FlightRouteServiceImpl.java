package com.dursun.airways.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dursun.airways.common.exception.ProcessException;
import com.dursun.airways.dao.mapper.FlightRouteMapper;
import com.dursun.airways.dao.repository.FlightRouteRepository;
import com.dursun.airways.domain.dto.AirPortsDTO;
import com.dursun.airways.domain.dto.FlightRouteDTO;
import com.dursun.airways.web.response.ResponseResult;

@Service
public class FlightRouteServiceImpl implements FlightRouteService {

	@Autowired
	private FlightRouteRepository repo;

	@Autowired
	FlightRouteMapper mapper;

	@Autowired
	AirPortsService aiportService;

	public FlightRouteServiceImpl(FlightRouteMapper mapper, FlightRouteRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Override
	public List<FlightRouteDTO> getAllFlightRoute() {
		return mapper.toFlightRouteDTOList(repo.findAll());
	}

	/**
	 * Yaratilacak olan rotaya gelen havaalanlari eger tanimli bir havaalani degilse
	 * hata doner
	 */
	@Override
	public ResponseResult createFlightRoute(FlightRouteDTO dto) {
		if(dto !=null && StringUtils.hasText(dto.getAirPortFrom())&& StringUtils.hasText(dto.getAirPortTo())) {
			dto.setAirPortFrom(dto.getAirPortFrom().toUpperCase());
			dto.setAirPortTo(dto.getAirPortTo().toUpperCase());
			AirPortsDTO airportDTO = aiportService.findAirPortsByShortName(dto.getAirPortFrom());
			if (airportDTO == null) {
				return throwException(ProcessException.INVALID_ROUTE);
			} else {
				AirPortsDTO airportDTOto = aiportService.findAirPortsByShortName(dto.getAirPortTo());
				if (airportDTOto == null) {
					return throwException(ProcessException.INVALID_ROUTE);
				} else {
					FlightRouteDTO values = mapper
							.toFlightRouteDTO(repo.findByAirPortFromAndAirPortTo(dto.getAirPortFrom(), dto.getAirPortTo()));
					if(values != null) {
						return throwException(ProcessException.RECORD_NOT_SAVE);
					}
					repo.save(mapper.toFlightRoute(dto));
				}
			}
			return new ResponseResult();
		}else {
			return throwException(ProcessException.REQUEST_ERROR);
		}
	}

	@Override
	public FlightRouteDTO recordControl(String from, String to) {
		 if(StringUtils.hasText(from)&& StringUtils.hasText(to)) {
			 FlightRouteDTO values = mapper
						.toFlightRouteDTO(repo.findByAirPortFromAndAirPortTo(from, to));
			 if(values != null) {
				 return values;
			 }else {
				 return null;
			 }
		 }else {
			 return null;
		 }
	}
	
	private ResponseResult throwException(Long id) {
		ResponseResult expDTO = ProcessException.decisionResultForException(id);
		if (expDTO != null) {
			return expDTO;
		}
		return null;
	}

}
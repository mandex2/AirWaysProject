package com.dursun.airways.domain.service;

import java.util.List;

import com.dursun.airways.domain.dto.FlightRouteDTO;
import com.dursun.airways.web.response.ResponseResult;

public interface FlightRouteService {
	
	List<FlightRouteDTO> getAllFlightRoute();

	ResponseResult createFlightRoute(FlightRouteDTO dto);
	
	FlightRouteDTO recordControl(String from,String to);
}

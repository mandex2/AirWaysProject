package com.dursun.airways.domain.service;

import java.util.List;

import com.dursun.airways.domain.dto.FlightDefinitionDTO;
import com.dursun.airways.domain.dto.FlightDefinitionsWithDetailsDTO;
import com.dursun.airways.web.request.RequestCreateFlightDefinition;
import com.dursun.airways.web.request.RequestUpdateFlightQuota;
import com.dursun.airways.web.response.ResponseResult;

public interface FlightDefinitionService {

	List<FlightDefinitionDTO> getAllFlightDefinition();

	FlightDefinitionDTO createFlightDefinition(RequestCreateFlightDefinition dto);
	
	ResponseResult updateFlightQuota(RequestUpdateFlightQuota dto);

	FlightDefinitionsWithDetailsDTO findFlightByID(Long flightID);

}

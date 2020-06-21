package com.dursun.airways.domain.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.dursun.airways.domain.dto.FlightDefinitionDetailsDTO;

public interface FlightDefinitionDetailsService {

	List<FlightDefinitionDetailsDTO> getAllFlightDefinitionDetails();

	FlightDefinitionDetailsDTO createFlightDefinitionDetails(FlightDefinitionDetailsDTO dto);
	
	Boolean recordControl(BigDecimal amount, Integer quota, Date flightDate);
	
	FlightDefinitionDetailsDTO getFlightDefinitionDetailsByID(Long ID);
}

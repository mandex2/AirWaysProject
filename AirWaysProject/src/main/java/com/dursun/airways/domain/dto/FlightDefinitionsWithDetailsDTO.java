package com.dursun.airways.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightDefinitionsWithDetailsDTO {

	private FlightDefinitionDetailsDTO detailsDTO;
	
	private FlightDefinitionDTO masterDTO;
}

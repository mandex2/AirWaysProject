package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.FlightDefinitionDetails;
import com.dursun.airways.domain.dto.FlightDefinitionDetailsDTO;

@Mapper(componentModel = "spring")
public interface FlightDefinitionDetailsMapper {
	
	FlightDefinitionDetailsMapper instance = Mappers.getMapper(FlightDefinitionDetailsMapper.class);

	FlightDefinitionDetails toFlightDefinitionDetails(FlightDefinitionDetailsDTO dto);

	FlightDefinitionDetailsDTO toFlightDefinitionDetailsDTO(FlightDefinitionDetails model);

	List<FlightDefinitionDetails> toFlightDefinitionDetailsList(List<FlightDefinitionDetailsDTO> dto);

	List<FlightDefinitionDetailsDTO> toFlightDefinitionDetailsDTOList(List<FlightDefinitionDetails> model);
}

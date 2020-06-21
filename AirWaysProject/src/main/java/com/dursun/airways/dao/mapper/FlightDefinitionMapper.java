package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.FlightDefinition;
import com.dursun.airways.domain.dto.FlightDefinitionDTO;

@Mapper(componentModel = "spring")
public interface FlightDefinitionMapper {

	FlightDefinitionMapper instance = Mappers.getMapper(FlightDefinitionMapper.class);

	FlightDefinition toFlightDefinition(FlightDefinitionDTO dto);

	FlightDefinitionDTO toFlightDefinitionDTO(FlightDefinition model);

	List<FlightDefinition> toFlightDefinitionList(List<FlightDefinitionDTO> dto);

	List<FlightDefinitionDTO> toFlightDefinitionDTOList(List<FlightDefinition> model);
}

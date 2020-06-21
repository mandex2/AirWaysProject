package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.FlightRoute;
import com.dursun.airways.domain.dto.FlightRouteDTO;

@Mapper(componentModel = "spring")
public interface FlightRouteMapper {

	FlightRouteMapper instance = Mappers.getMapper(FlightRouteMapper.class);

	FlightRoute toFlightRoute(FlightRouteDTO dto);

	FlightRouteDTO toFlightRouteDTO(FlightRoute model);

	List<FlightRoute> toFlightRouteList(List<FlightRouteDTO> dto);

	List<FlightRouteDTO> toFlightRouteDTOList(List<FlightRoute> model);
}

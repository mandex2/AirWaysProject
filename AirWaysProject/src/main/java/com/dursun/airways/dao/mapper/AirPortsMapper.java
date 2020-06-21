package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.AirPorts;
import com.dursun.airways.domain.dto.AirPortsDTO;

@Mapper(componentModel = "spring")
public interface AirPortsMapper {

	AirPortsMapper instance = Mappers.getMapper(AirPortsMapper.class);

	AirPorts toAirPorts(AirPortsDTO dto);

	AirPortsDTO toAirPortsDTO(AirPorts model);

	List<AirPorts> toAirPortsList(List<AirPortsDTO> dto);

	List<AirPortsDTO> toAirPortsDTOList(List<AirPorts> model);
}

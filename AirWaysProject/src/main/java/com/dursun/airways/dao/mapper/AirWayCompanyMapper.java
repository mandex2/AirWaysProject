package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.AirWayCompany;
import com.dursun.airways.domain.dto.AirWayCompanyDTO;

@Mapper(componentModel = "spring")
public interface AirWayCompanyMapper {
	
	AirWayCompanyMapper instance  =Mappers.getMapper(AirWayCompanyMapper.class);

	AirWayCompany toAirWayCompany(AirWayCompanyDTO dto);

	AirWayCompanyDTO toAirWayCompanyDTO(AirWayCompany model);

	List<AirWayCompany> toAirWayCompanyList(List<AirWayCompanyDTO> dto);

	List<AirWayCompanyDTO> toAirWayCompanyDTOList(List<AirWayCompany> model);
}

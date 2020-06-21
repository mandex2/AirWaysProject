package com.dursun.airways.domain.service;

import java.util.List;

import com.dursun.airways.domain.dto.AirWayCompanyDTO;
import com.dursun.airways.web.response.ResponseResult;

public interface AirWayCompanyService {

	List<AirWayCompanyDTO> getAllAirWayCompany();

	ResponseResult  createAirWayCompany(AirWayCompanyDTO dto);
	
	AirWayCompanyDTO recordControl(String name); 
}

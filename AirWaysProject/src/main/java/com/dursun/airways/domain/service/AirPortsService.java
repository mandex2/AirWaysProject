package com.dursun.airways.domain.service;

import java.util.List;

import com.dursun.airways.domain.dto.AirPortsDTO;
import com.dursun.airways.web.response.ResponseResult;

public interface AirPortsService {

	List<AirPortsDTO> getAllAirPorts();

	AirPortsDTO findAirPortsByShortName(String shortName);

	ResponseResult createAirPorts(AirPortsDTO dto);
}

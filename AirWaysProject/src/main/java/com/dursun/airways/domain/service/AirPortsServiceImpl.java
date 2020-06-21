package com.dursun.airways.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dursun.airways.common.exception.ProcessException;
import com.dursun.airways.dao.mapper.AirPortsMapper;
import com.dursun.airways.dao.repository.AirPortsRepository;
import com.dursun.airways.domain.dto.AirPortsDTO;
import com.dursun.airways.web.response.ResponseResult;

@Service
public class AirPortsServiceImpl implements AirPortsService {

	@Autowired
	private AirPortsRepository repo;

	@Autowired
	AirPortsMapper mapper;

	public AirPortsServiceImpl(AirPortsMapper mapper, AirPortsRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Override
	public List<AirPortsDTO> getAllAirPorts() {
		return mapper.toAirPortsDTOList(repo.findAll());
	}

	@Override
	public ResponseResult createAirPorts(AirPortsDTO dto) {
		if (dto != null && StringUtils.hasText(dto.getName()) && StringUtils.hasText(dto.getShortName())) {
			dto.setName(dto.getName().toUpperCase());
			dto.setShortName(dto.getShortName().toUpperCase());
			AirPortsDTO values = mapper
					.toAirPortsDTO(repo.findAirPortsByNameOrShortName(dto.getName(), dto.getShortName()));
			if (values != null) {
				return ProcessException.decisionResultForException(ProcessException.RECORD_NOT_SAVE);
			}
			mapper.toAirPortsDTO(repo.save(mapper.toAirPorts(dto)));
			return new ResponseResult();
		} else {
			return ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
	}

	@Override
	public AirPortsDTO findAirPortsByShortName(String shortName) {
		return mapper.toAirPortsDTO(repo.findAirPortsByShortName(shortName));
	}
}
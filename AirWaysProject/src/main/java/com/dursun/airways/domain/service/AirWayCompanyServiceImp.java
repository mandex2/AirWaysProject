package com.dursun.airways.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dursun.airways.common.exception.ProcessException;
import com.dursun.airways.dao.mapper.AirWayCompanyMapper;
import com.dursun.airways.dao.repository.AirWayCompanyRepository;
import com.dursun.airways.domain.dto.AirWayCompanyDTO;
import com.dursun.airways.web.response.ResponseResult;

@Service
public class AirWayCompanyServiceImp implements AirWayCompanyService {

	@Autowired
	private AirWayCompanyRepository repo;

	@Autowired
	AirWayCompanyMapper mapper;

	public AirWayCompanyServiceImp(AirWayCompanyMapper mapper, AirWayCompanyRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Override
	public List<AirWayCompanyDTO> getAllAirWayCompany() {
			return mapper.toAirWayCompanyDTOList(repo.findAll());
	}

	@Override
	public ResponseResult createAirWayCompany(AirWayCompanyDTO dto) {
		if (dto != null && StringUtils.hasText(dto.getName())) {
			dto.setName(dto.getName().toUpperCase());
			AirWayCompanyDTO values = mapper.toAirWayCompanyDTO(repo.findByName(dto.getName()));
			if (values != null) {
				return ProcessException.decisionResultForException(ProcessException.RECORD_NOT_SAVE);
			}
			mapper.toAirWayCompanyDTO(repo.save(mapper.toAirWayCompany(dto)));
			return new ResponseResult();
		} else {
			return ProcessException.decisionResultForException(ProcessException.REQUEST_ERROR);
		}
	}

	@Override
	public AirWayCompanyDTO recordControl(String name) {
		if (StringUtils.hasText(name) ) {
			AirWayCompanyDTO values = mapper.toAirWayCompanyDTO(repo.findByName(name));
			if (values != null) {
				return values;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

}

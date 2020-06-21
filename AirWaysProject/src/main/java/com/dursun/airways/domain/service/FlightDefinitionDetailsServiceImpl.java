package com.dursun.airways.domain.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dursun.airways.dao.entity.FlightDefinitionDetails;
import com.dursun.airways.dao.mapper.FlightDefinitionDetailsMapper;
import com.dursun.airways.dao.repository.FlightDefinitionDetailsRepository;
import com.dursun.airways.domain.dto.FlightDefinitionDetailsDTO;

@Service
public class FlightDefinitionDetailsServiceImpl implements FlightDefinitionDetailsService {

	@Autowired
	private FlightDefinitionDetailsRepository repo;

	@Autowired
	FlightDefinitionDetailsMapper mapper;

	public FlightDefinitionDetailsServiceImpl(FlightDefinitionDetailsMapper mapper,
			FlightDefinitionDetailsRepository repo) {
		this.mapper = mapper;
		this.repo = repo;
	}

	@Override
	public List<FlightDefinitionDetailsDTO> getAllFlightDefinitionDetails() {
		return mapper.toFlightDefinitionDetailsDTOList(repo.findAll());
	}

	@Override
	public FlightDefinitionDetailsDTO createFlightDefinitionDetails(FlightDefinitionDetailsDTO dto) {
		return mapper.toFlightDefinitionDetailsDTO(repo.save(mapper.toFlightDefinitionDetails(dto)));
	}

	@Override
	public Boolean recordControl(BigDecimal amount, Integer quota, Date flightDate) {
		if (flightDate != null && quota != null && amount != null) {
			FlightDefinitionDetailsDTO values = mapper
					.toFlightDefinitionDetailsDTO(repo.findByAmountAndQuotaAndFlightDate(amount, quota, flightDate));
			if (values != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public FlightDefinitionDetailsDTO getFlightDefinitionDetailsByID(Long ID) {
		Optional<FlightDefinitionDetails> details= repo.findById(ID);
		if(details.isPresent()) {
			return mapper.toFlightDefinitionDetailsDTO(details.get());
		}else {
			return null;
		}
	}
}

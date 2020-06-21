package com.dursun.airways.dao.repository;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.FlightDefinitionDetails;

public interface FlightDefinitionDetailsRepository
		extends JpaRepository<FlightDefinitionDetails, Long>, JpaSpecificationExecutor<FlightDefinitionDetails> {

	FlightDefinitionDetails findByAmountAndQuotaAndFlightDate(BigDecimal amount, Integer quota, Date flightDate);

}

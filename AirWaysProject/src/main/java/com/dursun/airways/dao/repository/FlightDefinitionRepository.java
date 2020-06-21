package com.dursun.airways.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.FlightDefinition;

public interface FlightDefinitionRepository
		extends JpaRepository<FlightDefinition, Long>, JpaSpecificationExecutor<FlightDefinition> {

	FlightDefinition findByFlightID(Long flightID);
}

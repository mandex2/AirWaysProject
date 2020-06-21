package com.dursun.airways.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.AirPorts;

public interface AirPortsRepository extends JpaRepository<AirPorts, Long>, JpaSpecificationExecutor<AirPorts> {

	AirPorts findAirPortsByShortName(String shortName);

	AirPorts findAirPortsByNameOrShortName(String name,String shortName);
}

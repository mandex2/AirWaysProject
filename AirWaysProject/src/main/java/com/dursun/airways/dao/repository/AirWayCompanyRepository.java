package com.dursun.airways.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.AirWayCompany;

public interface AirWayCompanyRepository
		extends JpaRepository<AirWayCompany, Long>, JpaSpecificationExecutor<AirWayCompany> {

	AirWayCompany findByName(String name);

}

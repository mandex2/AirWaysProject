package com.dursun.airways.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.FlightRoute;

public interface FlightRouteRepository extends JpaRepository<FlightRoute, Long>, JpaSpecificationExecutor<FlightRoute> {

	FlightRoute findByAirPortFromAndAirPortTo(String airPortFrom, String airPortTo);
}

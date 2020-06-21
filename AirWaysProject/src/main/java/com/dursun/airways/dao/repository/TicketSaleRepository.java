package com.dursun.airways.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dursun.airways.dao.entity.TicketSale;

public interface TicketSaleRepository extends JpaRepository<TicketSale, Long>, JpaSpecificationExecutor<TicketSale> {

	List<TicketSale> findTicketSaleByFlightID(Long flightID);

}

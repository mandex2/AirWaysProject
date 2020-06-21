package com.dursun.airways.domain.service;

import java.util.List;

import com.dursun.airways.domain.dto.TicketSaleDTO;
import com.dursun.airways.web.request.RequestTicketSale;
import com.dursun.airways.web.request.RequestTicketSaleCancel;

public interface TicketSaleService {

	List<TicketSaleDTO> getAllTicketSale();

	TicketSaleDTO createTicketSale(RequestTicketSale dto);
	
	TicketSaleDTO createTicketSaleCancel(RequestTicketSaleCancel dto);
	
	TicketSaleDTO recordControl (Long ticketId);
}

package com.dursun.airways.dao.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.dursun.airways.dao.entity.TicketSale;
import com.dursun.airways.domain.dto.TicketSaleDTO;

@Mapper(componentModel = "spring")
public interface TicketSaleMapper {
	
	TicketSaleMapper instance = Mappers.getMapper(TicketSaleMapper.class);

	TicketSale toTicketSale(TicketSaleDTO dto);

	TicketSaleDTO toTicketSaleDTO(TicketSale model);

	List<TicketSale> toTicketSaleList(List<TicketSaleDTO> dto);

	List<TicketSaleDTO> toTicketSaleDTOList(List<TicketSale> model);
}

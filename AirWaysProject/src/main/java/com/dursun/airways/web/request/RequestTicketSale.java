package com.dursun.airways.web.request;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestTicketSale {
	
	@ApiModelProperty(notes = "FLIGHT ID")
	private Long flightID;
	
	@ApiModelProperty(notes = "CARD NUMBER")
	public String cartNumber;
	
	@ApiModelProperty(notes = "AMOUNT")
	public BigDecimal amount;
	
	@ApiModelProperty(notes = "NAME")
	public String name;
	
	@ApiModelProperty(notes = "SURNAME")
	public String surName;
	
}

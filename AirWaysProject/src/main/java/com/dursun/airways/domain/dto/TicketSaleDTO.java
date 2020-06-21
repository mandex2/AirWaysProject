package com.dursun.airways.domain.dto;

import java.math.BigDecimal;

import com.dursun.airways.web.response.ResponseResult;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketSaleDTO extends ResponseResult{
	
	private Long id;

	@ApiModelProperty(notes = "FLIGHT_ID")
	private Long flightID;

	@ApiModelProperty(notes = "STATU")
	private String statu;

	@ApiModelProperty(notes = "CARD_NUMBER")
	private String cartNumber;

	@ApiModelProperty(notes = "AMOUNT")
	private BigDecimal amount;

	@ApiModelProperty(notes = "NAME")
	private String name;

	@ApiModelProperty(notes = "SURNAME")
	private String surname;
}

package com.dursun.airways.domain.dto;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightDefinitionDetailsDTO {

	private Long id;

	@ApiModelProperty(notes = "AMOUNT")
	private BigDecimal amount;

	@ApiModelProperty(notes = "QUOTA")
	private Integer quota;

	@ApiModelProperty(notes = "FLIGHT_DATE")
	private Date flightDate;

	@ApiModelProperty(notes = "TICKET_COUNT")
	private Integer ticketCount = 0;
}

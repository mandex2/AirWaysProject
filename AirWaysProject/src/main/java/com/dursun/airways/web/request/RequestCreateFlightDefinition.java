package com.dursun.airways.web.request;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestCreateFlightDefinition {
	
	@ApiModelProperty(notes="COMPANY NAME")
	public String companyName;
	
	@ApiModelProperty(notes = "AIRPORT FROM")
	private String airPortFrom;

	@ApiModelProperty(notes = "AIRPORT TO")
	private String airPortTo;
	
	@ApiModelProperty(notes="AMOUNT")
	public BigDecimal amount;
	
	@ApiModelProperty(notes="FLIGHT DATE")
	@DateTimeFormat(pattern="yyyy-MM-dd HH")
	public Date flightDate;
	
	@ApiModelProperty(notes="QUOTA")
	public Integer quota;

}

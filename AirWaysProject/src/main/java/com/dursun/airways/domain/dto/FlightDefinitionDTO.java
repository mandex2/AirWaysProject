package com.dursun.airways.domain.dto;

import com.dursun.airways.web.response.ResponseResult;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightDefinitionDTO extends ResponseResult{

	private Long flightID;

	@ApiModelProperty(notes = "ROUTE_ID")
	private Long routeID;

	@ApiModelProperty(notes = "COMPANY_ID")
	private Long companyID;

	@ApiModelProperty(notes = "DETAILS_ID")
	private Long detailsID;
}

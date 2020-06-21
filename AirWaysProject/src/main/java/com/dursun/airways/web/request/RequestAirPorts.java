package com.dursun.airways.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestAirPorts {

	@ApiModelProperty(notes = "COMPANY NAME")
	private String name;

	@ApiModelProperty(notes = "SHORT_NAME")
	private String shortName;

	@ApiModelProperty(notes = "CITY")
	private String city;
}

package com.dursun.airways.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestFlightRoute {

	@ApiModelProperty(notes = "AIRPORT_FROM")
	private String airPortFrom;

	@ApiModelProperty(notes = "AIRPORT_TO")
	private String airPortTo;
}

package com.dursun.airways.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightRouteDTO {

	private Long id;

	@ApiModelProperty(notes = "AIRPORT_FROM")
	private String airPortFrom;

	@ApiModelProperty(notes = "AIRPORT_TO")
	private String airPortTo;
}

package com.dursun.airways.domain.dto;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirPortsDTO {

	private Long id;
	
	@ApiModelProperty(notes = "COMPANY NAME")
	@NotNull
	private String name;

	@ApiModelProperty(notes = "SHORT_NAME")
	@NotNull
	private String shortName;

	@ApiModelProperty(notes = "CITY")
	@NotNull
	private String city;
}

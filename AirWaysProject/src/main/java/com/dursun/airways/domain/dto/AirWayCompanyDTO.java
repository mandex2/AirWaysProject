package com.dursun.airways.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirWayCompanyDTO {

	private Long id;
	
	@ApiModelProperty(notes="COMPANY NAME")
	private String name;
}

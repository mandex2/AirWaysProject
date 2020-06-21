package com.dursun.airways.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestUpdateFlightQuota {

	@ApiModelProperty(notes = "FLIGHT ID")
	private Long flightID;
	@ApiModelProperty(notes = "QUOTA")
	public Integer quota;
}

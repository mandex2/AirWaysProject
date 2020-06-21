package com.dursun.airways.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseCreateFlightDefinition extends ResponseResult {

	private Long flightID;
}

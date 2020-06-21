package com.dursun.airways.web.response;


import com.dursun.airways.common.enums.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {

	private ResponseStatus status = ResponseStatus.SUCCESS;
	private Long errorID;
	private String errorDescription;
}

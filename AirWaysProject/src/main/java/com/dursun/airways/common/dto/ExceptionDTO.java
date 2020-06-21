package com.dursun.airways.common.dto;

import com.dursun.airways.common.enums.ResponseStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionDTO {

	private Long errorID;
	private String errorDescription;
	
}

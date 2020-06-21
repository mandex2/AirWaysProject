package com.dursun.airways.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestTicketSaleCancel {

	@ApiModelProperty(notes = "TICKET ID")
	private Long ticketID;
}

package com.dursun.airways.common.exception;

import com.dursun.airways.common.enums.ResponseStatus;
import com.dursun.airways.common.utils.ResourceFactory;
import com.dursun.airways.web.response.ResponseResult;

public class ProcessException {

	public static ResourceFactory messages = ResourceFactory.getInstance();

	/*
	 * HATALI HAVALIMANI
	 */
	public static final Long INVALID_ROUTE = 1L;
	
	/*
	 * VAR OLAN KAYIT TEKRAR EKLENEMEZ
	 */
	public static final Long RECORD_NOT_SAVE = 2L;
	
	/*
	 * HATALI GIRDI
	 */
	public static final Long REQUEST_ERROR = 3L;
	
	/*
	 * TANIMSIZ ROTA
	 */
	public static final Long UNDEFINED_ROUTE = 4L;
	
	/*
	 * TANIMSIZ HAVAYOLU FIRMASI
	 */
	public static final Long UNDEFINED_AIRWAY_COMPANY = 5L;

	/*
	 * KAYIT BULUNAMADI
	 */
	public static final Long NO_DATA_FOUND = 6L;
	
	/*
	 * ILGILI UCUS BULUNAMADI
	 */
	public static final Long FLIGHT_NO_FOUND = 7L;
	
	/*
	 * KREDI KARTI BAKIYESI YETERSIZ
	 */
	public static final Long INSUFFICIENT_BALANCE = 8L;
	
	/*
	 * HATALI KREDI KARTI NUMARASI
	 */
	public static final Long INVALID_CREDIT_CARD = 9L;
	
	/*
	 * UCUSU GECMIS BILET ILE ISLEM YAPILAMAZ
	 */
	public static final Long FLIGHT_DATE_PASS = 10L;
	
	public static ResponseResult decisionResultForException(Long id) {
		ResponseResult dto = new ResponseResult();
		dto.setStatus(ResponseStatus.FAILURE);
		dto.setErrorID(id);
		dto.setErrorDescription(messages.getPropertyValue(ProcessException.class, id.toString()));
		return dto;
	}

}

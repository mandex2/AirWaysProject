package com.dursun.airways.common.enums;

public enum StatusEnum {
	ACTIVE("A"), PASSIVE("P");

	private final String value;

	private StatusEnum(String values) {
		this.value = values;
	}

	public String getValue() {
		return this.value;
	}
}

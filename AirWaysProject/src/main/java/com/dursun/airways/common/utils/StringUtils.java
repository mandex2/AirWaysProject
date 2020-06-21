package com.dursun.airways.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	public static String creditCardControl(String cardNumber) {
		Pattern pt = Pattern.compile("[^a-zA-Z0-9]");
		Matcher match = pt.matcher(cardNumber);
		while (match.find()) {
			String s = match.group();
			cardNumber = cardNumber.replaceAll("\\" + s, "");
		}
		return cardNumber.replaceAll("[^0-9]", "");
	}

	public static String creditCardMask(String cardNumber) {
		if (cardNumber.length() == 16) {
			return cardNumber.substring(0, 6) + "******" + cardNumber.substring(12, 16);
		} else {
			return null;
		}
	}
}

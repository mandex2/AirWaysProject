package com.dursun.airways.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;

public class DateUtils {
	private static Map<Enum<?>, SimpleDateFormat> dateFormatMap;

	public static enum DateFormatTag {
		/** dd/MM/yyyy **/
		DATE_FORMAT_1("yyyy-MM-dd HH:MM");

		private String format;

		private DateFormatTag(String format) {
			this.format = format;
		}

		public String getFormat() {
			return this.format;
		}

		public SimpleDateFormat getSimpleDateFormat() {
			return new SimpleDateFormat(this.format);
		}
	}

	public static String getCurrentDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DateFormatTag.DATE_FORMAT_1.getFormat());
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Istanbul"));
		return dateFormat.format(date);
	}

	public static Date stringToDate(String value, DateFormatTag formatTag) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		try {
			Date val = null;
			SimpleDateFormat formatter = formatTag.getSimpleDateFormat();
			if (formatter == null) {
				val = dateFormatMap.get(formatTag).parse(value);
			} else {
				val = formatter.parse(value);
			}
			return val;
		} catch (ParseException e) {
			return null;
		}
	}
}

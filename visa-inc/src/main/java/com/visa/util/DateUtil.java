package com.visa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy HH:mm";
	public static final String CREDIT_CARD_DATE_FORMAT = "dd/MM/yyyy HH:mm";

	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	public static final SimpleDateFormat creditCardDateFormatRead = new SimpleDateFormat(CREDIT_CARD_DATE_FORMAT);
	
	public String getCurrentDateTime() {
		try {
			return getCurrentDateTime(defaultDateFormat);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String getCurrentDateTime(SimpleDateFormat format) {
		try {
			return format.format(new Date());
		} catch (Exception e) {
			return null;
		}		
	}
		
	public Date parseTime(String timeText, SimpleDateFormat format) {
		try {
			return format.parse(timeText);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String formatTime(Date date, SimpleDateFormat format) {
		try {
			return format.format(date);
		} catch (Exception e) {
			return null;
		}
	}
	
	public String exchangeFormat(String timeText, SimpleDateFormat inFormat, SimpleDateFormat outFormat) {
		try {
			return formatTime(parseTime(timeText, inFormat), outFormat);
		} catch (Exception e) {
			return null;
		}
	}
}

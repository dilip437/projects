package com.card.payment.bank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy HH:mm";
	public static final String VALIDITY_DATE_FORMAT = "MM-yy";
	public static final String TRANSACTION_NUMBER_FORMAT = "ddMMyyyyHHmmss";

	public static final SimpleDateFormat defaultDateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	public static final SimpleDateFormat ccValidityDateFormat = new SimpleDateFormat(VALIDITY_DATE_FORMAT);
	public static final SimpleDateFormat transactionNumberFormat = new SimpleDateFormat(TRANSACTION_NUMBER_FORMAT);
	
	public String getTransactionNumber() {
		try {
			return getCurrentDateTime(transactionNumberFormat);
		} catch (Exception e) {
			return null;
		}		
	}
	
	public Date cardValidityToDate(String timeText) {
		try {
			return parseTime(timeText, ccValidityDateFormat);			
		} catch (Exception e) {
			return null;
		}
	}

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

package com.card.pos.service;

import org.springframework.stereotype.Component;

@Component
public class CardValidator {

	public boolean validate(String s) {
		if(!prefixMatched(s))
			return false; 
		if(s.length() == 15)
			return valid15(s);
		else if(s.length() == 16)
			return valid16(s);
		else
			return false;
	}
	
	private boolean prefixMatched(String s) {
		return s.startsWith("4") 
			|| s.startsWith("5") 
			|| s.startsWith("6") 
			|| s.startsWith("34")
			|| s.startsWith("37")
			|| s.startsWith("30")
			|| s.startsWith("36");
    	}

	private boolean valid15(String s) {
		boolean result = false;
		char[] ca = s.toCharArray();
		int[] ia = new int[ca.length];
		try {
			for(int i = 0; i < ca.length; i++) {
				ia[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			return false;
		}
		int sum = 0;
		for(int i = 0; i < ia.length-1; i++) {
			if(i%2 == 1) {
				int N = 2 * ia[i];
				if(N > 9) N = 1 + N % 10;
				ia[i] = N;
			}
			sum = sum + ia[i];
		}
		int r = sum % 10;
		if(r == 0 && ia[ia.length-1] == 0) {
			result = true;
		}else if((10 - r) == ia[ia.length-1]) {
			result = true;
		}
		return result;
	}
	
	private boolean valid16(String s) {
		boolean result = false;
		char[] ca = s.toCharArray();
		int[] ia = new int[ca.length];
		try {
			for(int i = 0; i < ca.length; i++) {
				ia[i] = Integer.parseInt(String.valueOf(ca[i]));
			}
		} catch (NumberFormatException e) {
			return false;
		}
		int sum = 0;
		for(int i = 0; i < ia.length-1; i++) {
			if(i%2 == 0) {
				int N = 2 * ia[i];
				if(N > 9) N = 1 + N % 10;
				ia[i] = N;
			}
			sum = sum + ia[i];
		}
		int r = sum % 10;
		if(r == 0 && ia[ia.length-1] == 0) {
			result = true;
		}else if((10 - r) == ia[ia.length-1]) {
			result = true;
		}
		return result;
	}
}

package com.visa.util;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public void sendEmail(String[] to, String from, String text) {
		System.out.println();
		System.out.println("------------------------------------------------------------------");
		System.out.println("     Email: " + text);
		System.out.println("------------------------------------------------------------------");
		System.out.println();
	}
}

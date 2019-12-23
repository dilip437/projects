package com.visa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PosInfo {
	private String number;
	private String pin;
	private String expiryDate;
	private Float amount;
}

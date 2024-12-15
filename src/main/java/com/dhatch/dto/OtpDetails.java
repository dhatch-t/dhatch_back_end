package com.dhatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OtpDetails {

	private String customerPhoneNumber;
	private int customerOtp;

}

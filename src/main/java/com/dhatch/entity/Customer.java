package com.dhatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {

	@Id
	private String customerId;
	private String customerPhoneNumber;
	private String customerName;
	private String customerGender;
	private String customerEmail;
	private int customerOtp;
	private String customerSessionId;

}

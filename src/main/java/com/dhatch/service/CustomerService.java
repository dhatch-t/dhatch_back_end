package com.dhatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhatch.dto.OtpDetails;
import com.dhatch.entity.Customer;

@Service
public interface CustomerService {

	public Customer customerLogin(String customerPhoneNumber, String customerSessionId);

	public String sendOtp(OtpDetails otpDetails);

	public Customer saveProfile(Customer customer);
	
	public String deleteCustomer(String customerPhoneNumber);
	
	public Customer getCustomerByCustomerPhoneNumber(String customerPhoneNumber);
	
	public List<Customer> getAllCustomerDetails();
}

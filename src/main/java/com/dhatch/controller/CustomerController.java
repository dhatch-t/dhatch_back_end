package com.dhatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhatch.dto.OtpDetails;
import com.dhatch.entity.Customer;
import com.dhatch.service.CustomerService;


@RestController
@RequestMapping("/Customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@PostMapping("/customerLogin/{customerPhoneNumber}/{customerSessionId}")
	public ResponseEntity<Customer> customerLoginController(@PathVariable String customerPhoneNumber,
			@PathVariable String customerSessionId) {
		return new ResponseEntity<Customer>(customerService.customerLogin(customerPhoneNumber, customerSessionId),
				HttpStatus.OK);
	}

	@PostMapping("/sendOtp")
	public ResponseEntity<String> sendOtpController(@RequestBody OtpDetails otpDetails) {
		return new ResponseEntity<String>(customerService.sendOtp(otpDetails), HttpStatus.OK);
	}

	@PostMapping("/saveCustomerProfile")
	public ResponseEntity<Customer> saveCustomerProfileController(@RequestBody Customer customer) {
		return new ResponseEntity<Customer>(customerService.saveProfile(customer), HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteProfile/{customerPhoneNumber}")
	public ResponseEntity<String> deleteProfileController(@PathVariable String customerPhoneNumber) {
		return new ResponseEntity<String>(customerService.deleteCustomer(customerPhoneNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getCustomerByCustomerPhoneNumber/{customerPhoneNumber}")
	public ResponseEntity<Customer> getCustomerByCustomerPhoneNumberController(@PathVariable String customerPhoneNumber) {
		return new ResponseEntity<Customer>(customerService.getCustomerByCustomerPhoneNumber(customerPhoneNumber), HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomerDetails")
	public ResponseEntity<List<Customer>> getAllCustomerDetailsController() {
		return new ResponseEntity<List<Customer>>(customerService.getAllCustomerDetails(), HttpStatus.OK);
	}

}

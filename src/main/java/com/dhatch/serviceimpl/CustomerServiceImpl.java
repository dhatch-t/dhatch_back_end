package com.dhatch.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhatch.dto.OtpDetails;
import com.dhatch.entity.Customer;
import com.dhatch.exception.CustomerNotFoundException;
import com.dhatch.exception.OtpNotFoundException;
import com.dhatch.repository.CustomerRepository;
import com.dhatch.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRespository;

	@Override
	public Customer customerLogin(String customerPhoneNumber, String customerSessionId) {

		Optional<Customer> customer = customerRespository.findByCustomerPhoneNumber(customerPhoneNumber);
		if (customer.isEmpty()) {

			String customerId = String.format("%s%06d", "C", customerRespository.findAll().size() + 1);

			Customer cus = new Customer();
			cus.setCustomerId(customerId); 
			cus.setCustomerPhoneNumber(customerPhoneNumber);
			cus.setCustomerSessionId(customerSessionId);
			cus.setCustomerOtp(1234);
			return customerRespository.save(cus);

		} else {

			if (!customer.get().getCustomerSessionId().equalsIgnoreCase(customerSessionId)) {
				customer.get().setCustomerSessionId(customerSessionId);
			}
			customer.get().setCustomerOtp(1234);
			return customerRespository.save(customer.get());

		}

	}

	@Override
	public String sendOtp(OtpDetails otpDetails) {
		String status;
		Optional<Customer> customer = customerRespository
				.findByCustomerPhoneNumber(otpDetails.getCustomerPhoneNumber());
		if (!customer.isEmpty()) {
			if (customer.get().getCustomerOtp() == otpDetails.getCustomerOtp()) {
				if (customer.get().getCustomerName() != null) {
					status = "Existing";
				} else {
					status = "New";
				}
			} else {
				throw new OtpNotFoundException("Customer OTP not match");
			}

		} else {
			throw new CustomerNotFoundException("Customer Details not found");
		}
		return status;

	}

	@Override
	public Customer saveProfile(Customer customer) {
		Optional<Customer> customerDetails = customerRespository
				.findByCustomerPhoneNumber(customer.getCustomerPhoneNumber());
		if (!customerDetails.isEmpty()) {
			Customer cus = customerDetails.get();
			cus.setCustomerName(customer.getCustomerName());
			cus.setCustomerEmail(customer.getCustomerEmail());
			cus.setCustomerGender(customer.getCustomerGender());
			return customerRespository.save(cus);
		} else {
			throw new CustomerNotFoundException("Customer Details not found");
		}
	}

	@Override
	public String deleteCustomer(String customerPhoneNumber) {
		Optional<Customer> customerDetails = customerRespository.findByCustomerPhoneNumber(customerPhoneNumber);
		if (!customerDetails.isEmpty()) {
			customerRespository.deleteByCustomerPhoneNumber(customerPhoneNumber);
			return "Customer Details are deleted";
		} else {
			throw new CustomerNotFoundException("Customer Details not found");
		}
	}

	@Override
	public Customer getCustomerByCustomerPhoneNumber(String customerPhoneNumber) {
		Optional<Customer> customerDetails = customerRespository.findByCustomerPhoneNumber(customerPhoneNumber);
		if (!customerDetails.isEmpty()) {
			return customerRespository.findByCustomerPhoneNumber(customerPhoneNumber).get();
		} else {
			throw new CustomerNotFoundException("Customer Details not found");
		}
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		List<Customer> customerList = customerRespository.findAll();
		if (!customerList.isEmpty()) {
			return customerList;
		} else {
			throw new CustomerNotFoundException("Customer Details not found");
		}
	}

}

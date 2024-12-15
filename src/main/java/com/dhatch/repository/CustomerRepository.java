package com.dhatch.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhatch.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
	Optional<Customer> findByCustomerPhoneNumber(String customerPhoneNumber);

	void deleteByCustomerPhoneNumber(String customerPhoneNumber);

}

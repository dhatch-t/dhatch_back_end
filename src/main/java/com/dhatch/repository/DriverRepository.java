
package com.dhatch.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhatch.entity.Driver;

@Repository 
public interface DriverRepository extends JpaRepository<Driver, String>{
	public List<Driver> findByDriverPhone(String driverPhone);
	public Boolean existsByDriverPhone(String driverPhone);
}


package com.dhatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhatch.entity.Driver;

@Service
public interface DriverService {
	public Driver enrollDriver(Driver driver);
	public Driver editDriverInfo(String driverId, Driver driver);
	public String deleteDriver(String driverId);
	public Driver findDriver(String driverId);
	public List<Driver> getAllDrivers();
	public String sendOtp(String driverPhone);
	public Boolean verifyOtp(String phoneNumber, int otp, String sessionId);
}

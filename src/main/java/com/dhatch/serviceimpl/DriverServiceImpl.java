
package com.dhatch.serviceimpl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhatch.entity.Driver;
import com.dhatch.exception.DriverNotFoundException;
import com.dhatch.exception.OtpNotFoundException;
import com.dhatch.repository.DriverRepository;
import com.dhatch.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository;

	@Override
	public Driver enrollDriver(Driver driver) { // D1. D0001
		return driverRepository.save(driver);
	}

	@Override
	public Driver editDriverInfo(String driverId, Driver newInfo) {
		if (driverRepository.existsById(driverId)) {
			Driver driver = driverRepository.getById(driverId);
			if (!newInfo.getDriverName().isEmpty() || newInfo.getDriverName() != null) {
				driver.setDriverName(newInfo.getDriverName());
			}
			if (newInfo.getDriverPhone() != null) {
				driver.setDriverPhone(newInfo.getDriverPhone());
			}
			if (newInfo.getDriverAge() != 0) {
				driver.setDriverAge(newInfo.getDriverAge());
			}
			if (!newInfo.getDriverEmail().isEmpty() || newInfo.getDriverEmail() != null) {
				driver.setDriverEmail(newInfo.getDriverEmail());
			}
			if (!newInfo.getDriverGender().isEmpty() || newInfo.getDriverGender() != null) {
				driver.setDriverGender(newInfo.getDriverGender());
			}
			return driverRepository.save(driver);
		} else {
			throw new DriverNotFoundException("Driver with Driver ID: " + driverId + " not found!");
		}
	}

	@Override
	public String deleteDriver(String driverId) {
		if (driverRepository.existsById(driverId)) {
			driverRepository.deleteById(driverId);
			boolean driverIdExists = driverRepository.existsById(driverId);
			if (driverIdExists) {
				return "Delete request failed!";
			} else {
				return "Driver ID: " + driverId + "deleted successfully";
			}
		} else {
			throw new DriverNotFoundException("Driver with Driver ID: " + driverId + " not found!");
		}
	}

	@Override
	public Driver findDriver(String driverId) {
		if (driverRepository.existsById(driverId)) {
			return driverRepository.findById(driverId).get();
		} else {
			throw new DriverNotFoundException("Driver with Driver ID: " + driverId + " not found!");
		}
	}

	@Override
	public List<Driver> getAllDrivers() {
		return driverRepository.findAll();
	}

	@Override
	public String sendOtp(String driverPhone) {
		if (driverRepository.existsByDriverPhone(driverPhone)) {
			Random random = new Random();
			int otp = random.nextInt(9000) + 1000;
			Driver newOtp = new Driver();
			newOtp.setDriverPhone(driverPhone);
			newOtp.setDriverOtp(otp);
			// newOtp.setDriverSessionId(sessionId);
			driverRepository.save(newOtp);
		}
		if (driverRepository.existsById(driverPhone)) {
			Driver otp = driverRepository.findById(driverPhone).get();
			if (otp.getDriverOtp() != 0) {
				// write send otp to mobile number logic with sms api
				return "OTP sent successfully!";
			} else {
				throw new OtpNotFoundException("Failed to send OTP!");
			}
		} else {
			throw new OtpNotFoundException("Driver Phone Number doesn't exists!");
		}
	}

	@Override
	public Boolean verifyOtp(String phoneNumber, int otp, String sessionId) {
		if (driverRepository.existsById(phoneNumber)) {
			Driver existingdriverOtpObject = driverRepository.findById(phoneNumber).get();
			if (existingdriverOtpObject.getDriverOtp() == otp) {
				existingdriverOtpObject.setDriverSessionId(sessionId);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}

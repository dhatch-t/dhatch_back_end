package com.dhatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhatch.entity.Driver;
import com.dhatch.entity.DriverLocation;
import com.dhatch.service.DriverLocationService;
import com.dhatch.service.DriverService;

@RestController
@RequestMapping("/Driver")
public class DriverController {

	@Autowired
	DriverService driverService;

	@Autowired
	DriverLocationService driverLocationService;

	@PostMapping("/enrollDriver")
	public ResponseEntity<Driver> addNewDriver(@RequestBody Driver driver) {

		return new ResponseEntity<Driver>(driverService.enrollDriver(driver), HttpStatus.OK);
	}

	@PutMapping("/updateDriverInfo/{driverId}")
	public ResponseEntity<Driver> editDriverInfo(@PathVariable String driverId, @RequestBody Driver driver) {

		return new ResponseEntity<Driver>(driverService.editDriverInfo(driverId, driver), HttpStatus.OK);
	}

	@GetMapping("/getAllDrivers")
	public ResponseEntity<List<Driver>> getAllDivers() {

		return new ResponseEntity<List<Driver>>(driverService.getAllDrivers(), HttpStatus.OK);
	}

	@GetMapping("/getDriver/{driverId}")
	public ResponseEntity<Driver> getDriverById(@PathVariable String driverId) {

		return new ResponseEntity<Driver>(driverService.findDriver(driverId), HttpStatus.OK);
	}

	@DeleteMapping("/deleteDriver/{driverId}")
	public ResponseEntity<String> deleteDriverData(@PathVariable String driverId) {

		return new ResponseEntity<String>(driverService.deleteDriver(driverId), HttpStatus.OK);
	}

	@PostMapping("/sendOtpToDriver/{driverPhone}")
	public ResponseEntity<String> sendOtpToDriver(@PathVariable String driverPhone) {
		return new ResponseEntity<String>(driverService.sendOtp(driverPhone), HttpStatus.OK);
	}

	@PostMapping("/verifyDriverOtp/{phoneNumber}/{otp}/{sessionId}")
	public ResponseEntity<Boolean> verifyDriverOtp(@PathVariable String phoneNumber, @PathVariable int otp,
			@PathVariable String sessionId) {
		return new ResponseEntity<Boolean>(driverService.verifyOtp(phoneNumber, otp, sessionId), HttpStatus.OK);
	}

	@PostMapping("/location/{driverId}/{latitude}/{longitude}")
	public void saveDriverLocation(@PathVariable String driverId, @RequestBody Double latitude,
			@RequestBody Double longitude) {
		DriverLocation location = new DriverLocation();
		location.setDriverId(driverId);
		location.setLatitude(latitude);
		location.setLongitude(longitude);
		driverLocationService.saveDriverLocation(driverId, location);
	}

	@GetMapping("/{driverId}/location")
	public DriverLocation getDriverLocation(@PathVariable String driverId) {
		return driverLocationService.getDriverLocation(driverId);
	}
}
package com.dhatch.service;

import org.springframework.stereotype.Service;

import com.dhatch.entity.DriverLocation;

@Service
public interface DriverLocationService {
	public void saveDriverLocation(String driverId, DriverLocation location);
	public DriverLocation getDriverLocation(String driverId);
}

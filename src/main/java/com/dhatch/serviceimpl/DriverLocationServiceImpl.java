package com.dhatch.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dhatch.entity.DriverLocation;
import com.dhatch.service.DriverLocationService;

@Service
public class DriverLocationServiceImpl implements DriverLocationService {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	// Store driver location in Redis
	public void saveDriverLocation(String driverId, DriverLocation location) {
		redisTemplate.opsForValue().set("driverLocation:" + driverId, location);
	}

	// Get driver location from Redis
	public DriverLocation getDriverLocation(String driverId) {
		return (DriverLocation) redisTemplate.opsForValue().get("driverLocation:" + driverId);
	}
}

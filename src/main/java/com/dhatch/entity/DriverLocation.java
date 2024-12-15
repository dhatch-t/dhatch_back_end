package com.dhatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DriverLocation {
	@Id
	private String driverId;
	private double latitude;
    private double longitude;
    private String driverStatus;
    private String cabNumber;
    private String cabType;
}

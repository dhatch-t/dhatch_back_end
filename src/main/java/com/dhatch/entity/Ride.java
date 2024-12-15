package com.dhatch.entity;

import java.sql.Time;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Ride {
	@Id
	public String rideId;
	public int rideOtp;
	public String pickupLocation;
	public String dropLocation;
	public float estimatedDistance;
	public float estimatedFare;
	public float actualDistance;
	public float actualFare;
	public String rideStatus;
	public String cabNumber;
	public String cabType;
	public Time rideBookingTime;
	public Time rideStartTime;
	public Time rideEndtime;
	public Boolean paymentStatus;
	public String paymentMethod;
	public String paymentTransactionId;
	public int rideRating;
	public String rideReview;
	
	@OneToOne
    @JoinColumn(name = "customerId")  
    public Customer customer;
	
	@OneToOne
    @JoinColumn(name = "driverId") 
    public Driver driver;
}

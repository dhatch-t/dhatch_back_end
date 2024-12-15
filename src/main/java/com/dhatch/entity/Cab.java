package com.dhatch.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cab {
	@Id
	public String cabNumber;
	public String cabModel;
	public String cabType;
	
	@OneToOne
    @JoinColumn(name = "driver_id")  // Ensure unique driver for each cab
    private Driver driver;
}

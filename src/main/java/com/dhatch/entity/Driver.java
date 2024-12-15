
package com.dhatch.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Driver {
	@Id
	public String driverId;
	public String driverName;
	public String driverPhone;
	public String driverGender;
	public int driverAge;
	public String driverEmail;
	public int driverOtp;
	public String driverSessionId;
	public String licenseNumber;
	public int driverRating;
	public Date joiningDate;
}

package com.dhatch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CabDriverDTO {

    private String cabNumber;
    private String cabModel;
    private String cabType;
    private String driverId;
    private String driverName;
    private String driverGender;
    private int driverAge;
    private String driverPhone;

}

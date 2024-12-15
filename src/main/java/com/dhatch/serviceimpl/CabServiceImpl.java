package com.dhatch.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhatch.dto.CabDriverDTO;
import com.dhatch.entity.Cab;
import com.dhatch.entity.Driver;
import com.dhatch.exception.CabNotFoundException;
import com.dhatch.repository.CabRepository;
import com.dhatch.repository.DriverRepository;
import com.dhatch.service.CabService;

@Service
public class CabServiceImpl implements CabService {

	@Autowired
	private CabRepository cabRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Override
	public CabDriverDTO getCabWithDriverDetails(String cabNumber) {
		Optional<Cab> cabOpt = cabRepository.findById(cabNumber);
		if (cabOpt.isPresent()) {
			Cab cab = cabOpt.get();
			Driver driver = cab.getDriver();

			// Map to DTO
			return new CabDriverDTO(cab.getCabNumber(), cab.getCabModel(), cab.getCabType(), driver.getDriverId(),
					driver.getDriverName(), driver.getDriverGender(), driver.getDriverAge(), driver.getDriverPhone());
		} else {
			throw new CabNotFoundException("Cab Details not found");
		}

	}

	@Override
	public CabDriverDTO createCab(CabDriverDTO cabDriverDTO) {
		// Fetch driver details
		Driver driver = driverRepository.findById(cabDriverDTO.getDriverId())
				.orElseThrow(() -> new RuntimeException("Driver not found"));

		// Create a new Cab
		Cab cab = new Cab();
		cab.setCabNumber(cabDriverDTO.getCabNumber());
		cab.setCabModel(cabDriverDTO.getCabModel());
		cab.setCabType(cabDriverDTO.getCabType());
		cab.setDriver(driver);

		// Save the Cab in the database
		cab = cabRepository.save(cab);

		// Return the Cab and Driver details in a DTO
		return new CabDriverDTO(cab.getCabNumber(), cab.getCabModel(), cab.getCabType(), driver.getDriverId(),
				driver.getDriverName(), driver.getDriverGender(), driver.getDriverAge(), driver.getDriverPhone());
	}

	@Override
	public CabDriverDTO updateCab(String cabNumber, CabDriverDTO cabDriverDTO) {
		Optional<Cab> cabOpt = cabRepository.findById(cabNumber);
		if (cabOpt.isPresent()) {
			Cab cab = cabOpt.get();
			// If a new driver is provided, fetch and update the driver
			if (cabDriverDTO.getDriverId() != cab.getDriver().getDriverId()) {
				Driver newDriver = driverRepository.findById(cabDriverDTO.getDriverId())
						.orElseThrow(() -> new RuntimeException("Driver not found"));
				cab.setDriver(newDriver);
			}
			// Update cab details
			cab.setCabModel(cabDriverDTO.getCabModel());
			cab.setCabType(cabDriverDTO.getCabType());

			// Save the updated cab
			cab = cabRepository.save(cab);

			// Return updated cab details as DTO
			return new CabDriverDTO(cab.getCabNumber(), cab.getCabModel(), cab.getCabType(),
					cab.getDriver().getDriverId(), cab.getDriver().getDriverName(), cab.getDriver().getDriverGender(),
					cab.getDriver().getDriverAge(), cab.getDriver().getDriverPhone());
		} else {
			throw new CabNotFoundException("Cab OTP Details not found");
		}
		// Cab not found
	}

	@Override
	public String deleteCab(String cabNumber) {
		Optional<Cab> cabOpt = cabRepository.findById(cabNumber);
		if (cabOpt.isPresent()) {
			cabRepository.delete(cabOpt.get());
			return "Cab Details are deleted";
		} else {
			throw new CabNotFoundException("Cab OTP Details not found");
		}

	}

	@Override
	public List<CabDriverDTO> getAllCab() {
		List<Cab> cabs = cabRepository.findAll();
		if (!cabs.isEmpty()) {
			return cabs.stream().map(cab -> {
				Driver driver = cab.getDriver();
				return new CabDriverDTO(cab.getCabNumber(), cab.getCabModel(), cab.getCabType(), driver.getDriverId(),
						driver.getDriverName(), driver.getDriverGender(), driver.getDriverAge(),
						driver.getDriverPhone());
			}).collect(Collectors.toList());
		} else {
			throw new CabNotFoundException("Cab OTP Details not found");
		}

	}
}

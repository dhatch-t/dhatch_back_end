package com.dhatch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhatch.dto.CabDriverDTO;
import com.dhatch.service.CabService;

@RestController
@RequestMapping("/cabDetails")
public class CabController {
	@Autowired
	private CabService cabService;
//
//    @GetMapping("/cab/{cabNumber}")
//    public CabDriverDTO getCabDetailsWithDriver(@PathVariable String cabNumber) {
//        return cabService.getCabWithDriverDetails(cabNumber);
//    }

	@GetMapping("/getAllCab")
	public ResponseEntity<List<CabDriverDTO>> showAllCabs() {
		return new ResponseEntity<List<CabDriverDTO>>(cabService.getAllCab(), HttpStatus.OK);
	}

	@PostMapping("/addCab")
	public ResponseEntity<CabDriverDTO> createCab(@RequestBody CabDriverDTO cabDriverDTO) {

		return new ResponseEntity<CabDriverDTO>(cabService.createCab(cabDriverDTO), HttpStatus.OK);

	}

	@GetMapping("/getCab/{cabNumber}")
	public ResponseEntity<CabDriverDTO> getCabDetails(@PathVariable String cabNumber) {

		return new ResponseEntity<CabDriverDTO>(cabService.getCabWithDriverDetails(cabNumber), HttpStatus.OK);
	}

	@PutMapping("/updateCab/{cabNumber}")
	public ResponseEntity<CabDriverDTO> updateCab(@PathVariable String cabNumber,
			@RequestBody CabDriverDTO cabDriverDTO) {

		return new ResponseEntity<CabDriverDTO>(cabService.updateCab(cabNumber, cabDriverDTO), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCab/{cabNumber}")
	public ResponseEntity<String> deleteCab(@PathVariable String cabNumber) {
		return new ResponseEntity<String>(cabService.deleteCab(cabNumber), HttpStatus.OK);
	}
}

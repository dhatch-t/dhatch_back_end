package com.dhatch.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhatch.dto.CabDriverDTO;

@Service
public interface CabService {
	public CabDriverDTO getCabWithDriverDetails(String cabNumber);
	public CabDriverDTO createCab(CabDriverDTO cabDriverDTO);
	public CabDriverDTO updateCab(String cabNumber, CabDriverDTO cabDriverDTO);
	public String deleteCab(String cabNumber);
	public List<CabDriverDTO> getAllCab();
}

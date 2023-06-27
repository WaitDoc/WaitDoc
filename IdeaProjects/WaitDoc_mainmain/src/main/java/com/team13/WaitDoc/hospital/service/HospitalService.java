package com.team13.WaitDoc.hospital.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HospitalService {

	private final HospitalRepository hospitalRepository;


	public String getHospitalName(Long hospitalId) {
		Hospital hospital = hospitalRepository.findById(hospitalId)
			.orElseThrow(() -> new IllegalArgumentException("Invalid hospital Id:" + hospitalId));
		return hospital.getName();
	}

	public Hospital getHospital(Long hospitalId) {
		return hospitalRepository.findById(hospitalId)
			.orElseThrow(() -> new NoSuchElementException("No hospital found with ID: " + hospitalId));
	}
}

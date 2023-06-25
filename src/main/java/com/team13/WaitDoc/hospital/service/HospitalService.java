package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;
    public Optional<Hospital> findByHpid (String hpid){
        return hospitalRepository.findByHpid(hpid);
    }
}

package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;

import java.util.List;

public interface HospitalDslRepository {
    public List<Hospital> search(CategoryRequestDTO categoryRequestDTO);
}

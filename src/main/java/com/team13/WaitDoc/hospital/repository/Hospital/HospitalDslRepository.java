package com.team13.WaitDoc.hospital.repository.Hospital;

import com.team13.WaitDoc.category.DTO.CategoryRequestDTO;
import com.team13.WaitDoc.hospital.entity.Hospital;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HospitalDslRepository {
    public List<Hospital> search(CategoryRequestDTO categoryRequestDTO, Pageable pageable);
}

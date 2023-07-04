package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.hospital.entity.Department;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public Department findByName(String name){
        return departmentRepository.findByName(name).orElse(null);
    }

    public void saveAll(List<Hospital> hospitals){
        List<Department> departments = new ArrayList<>();
        for(Hospital hospital: hospitals){
            if(hospital.getDepartment() != null){
                for(String name : hospital.getDepartment().split(",")){
                    departments.add(
                            Department.builder()
                                    .name(name)
                                    .hospital(hospital)
                                    .build()
                    );
                }
            }
        }
        departmentRepository.saveAll(departments);
    }
}

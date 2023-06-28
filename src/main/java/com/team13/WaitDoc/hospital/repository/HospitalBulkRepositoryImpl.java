package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalBulkRepositoryImpl {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void bulkSave(List<Hospital> hospitals) {
        batchInsert(1000, hospitals);
    }

    private void batchInsert(int batchSize, List<Hospital> hospitals){
        jdbcTemplate.batchUpdate(
                "INSERT INTO hospital (id, name, hpid, department) " +
                        "VALUES (?, ?, ?, ?)",
                hospitals,
                batchSize,
                (ps, argument) -> {
                    ps.setLong(1, argument.getId());
                    ps.setString(2, argument.getName());

                    ps.setString(3, argument.getHpid());
                    ps.setString(4, argument.getDepartment());
                });
    }


}

package com.team13.WaitDoc.hospital.repository;

import com.team13.WaitDoc.base.config.AppConfig;
import com.team13.WaitDoc.hospital.entity.Hospital;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class HospitalBulkRepository {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public void bulkSave(List<Hospital> hospitals) {
        batchInsert(hospitals);
    }

    private void batchInsert(List<Hospital> hospitals){
        jdbcTemplate.batchUpdate(
                "INSERT INTO hospital (name, hpid, department, " +
                        "fri_start_time, fri_end_time, " +
                        "holiday_start_time, holiday_end_time, " +
                        "latitude, longitude, " +
                        "mon_start_time, mon_end_time, " +
                        "sat_start_time, sat_end_time, " +
                        "sun_start_time, sun_end_time, " +
                        "tue_start_time, tue_end_time, " +
                        "wed_start_time, wed_end_time, " +
                        "create_date, modify_date, " +
                        "addr, introduction, " +
                        "thu_start_time, thu_end_time, " +
                        "waiting_number) "+
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                hospitals,
                AppConfig.getBatchSize(),
                (ps, argument) -> {
                    ps.setString(1, argument.getName());
                    ps.setString(2, argument.getHpid());
                    ps.setString(3, argument.getDepartment());
                    ps.setInt(4, argument.getFriStartTime());
                    ps.setInt(5, argument.getFriEndTime());
                    ps.setInt(6, argument.getHolidayStartTime());
                    ps.setInt(7, argument.getHolidayEndTime());
                    ps.setDouble(8, argument.getLatitude());
                    ps.setDouble(9, argument.getLongitude());
                    ps.setInt(10, argument.getMonStartTime());
                    ps.setInt(11, argument.getMonEndTime());
                    ps.setInt(12, argument.getSatStartTime());
                    ps.setInt(13, argument.getSatEndTime());
                    ps.setInt(14, argument.getSunStartTime());
                    ps.setInt(15, argument.getSunEndTime());
                    ps.setInt(16, argument.getTueStartTime());
                    ps.setInt(17, argument.getTueEndTime());
                    ps.setInt(18, argument.getWedStartTime());
                    ps.setInt(19, argument.getWedEndTime());
                    ps.setTimestamp(20, Timestamp.valueOf(LocalDateTime.now()));
                    ps.setTimestamp(21, null);
                    ps.setString(22, argument.getAddr());
                    ps.setString(23, argument.getIntroduction());
                    ps.setInt(24, argument.getThuStartTime());
                    ps.setInt(25, argument.getThuEndTime());
                    ps.setInt(26, argument.getWaitingNumber());





                });
    }


}

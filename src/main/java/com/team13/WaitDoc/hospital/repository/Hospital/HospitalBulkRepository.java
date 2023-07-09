//package com.team13.WaitDoc.hospital.repository;
//
//import com.team13.WaitDoc.base.config.AppConfig;
//import com.team13.WaitDoc.hospital.entity.Hospital;
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.Time;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class HospitalBulkRepository {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Transactional
//    public void bulkSave(List<Hospital> hospitals) {
//        batchInsert(hospitals);
//    }
//
//    private void batchInsert(List<Hospital> hospitals){
//        jdbcTemplate.batchUpdate(
//                "INSERT INTO hospital (name, hpid, department, " +
//                        "fri_start_time, fri_end_time, " +
//                        "holiday_start_time, holiday_end_time, " +
//                        "latitude, longitude, " +
//                        "mon_start_time, mon_end_time, " +
//                        "sat_start_time, sat_end_time, " +
//                        "sun_start_time, sun_end_time, " +
//                        "tue_start_time, tue_end_time, " +
//                        "wed_start_time, wed_end_time, " +
//                        "create_date, modify_date, " +
//                        "addr, introduction, " +
//                        "thu_start_time, thu_end_time, " +
//                        "waiting_number) "+
//                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
//                hospitals,
//                AppConfig.getBatchSize(),
//                (ps, argument) -> {
//                    ps.setString(1, argument.getName());
//                    ps.setString(2, argument.getHpid());
//                    ps.setString(3, argument.getDepartment());
//                    ps.setTime(4, Time.valueOf(argument.getFriStartTime()));
//                    ps.setTime(5, Time.valueOf(argument.getFriEndTime()));
//                    ps.setTime(6, Time.valueOf(argument.getHolidayStartTime()));
//                    ps.setTime(7, Time.valueOf(argument.getHolidayEndTime()));
//                    ps.setDouble(8, argument.getLatitude());
//                    ps.setDouble(9, argument.getLongitude());
//                    ps.setTime(10, Time.valueOf(argument.getMonStartTime()));
//                    ps.setTime(11, Time.valueOf(argument.getMonEndTime()));
//                    ps.setTime(12, Time.valueOf(argument.getSatStartTime()));
//                    ps.setTime(13, Time.valueOf(argument.getSatEndTime()));
//                    ps.setTime(14, Time.valueOf(argument.getSunStartTime()));
//                    ps.setTime(15, Time.valueOf(argument.getSunEndTime()));
//                    ps.setTime(16, Time.valueOf(argument.getTueStartTime()));
//                    ps.setTime(17, Time.valueOf(argument.getTueEndTime()));
//                    ps.setTime(18, Time.valueOf(argument.getWedStartTime()));
//                    ps.setTime(19, Time.valueOf(argument.getWedEndTime()));
//                    ps.setTimestamp(20, Timestamp.valueOf(LocalDateTime.now()));
//                    ps.setTimestamp(21, null);
//                    ps.setString(22, argument.getAddr());
//                    ps.setString(23, argument.getIntroduction());
//                    ps.setTime(24, Time.valueOf(argument.getThuStartTime()));
//                    ps.setTime(25, Time.valueOf(argument.getThuEndTime()));
//                    ps.setInt(26, argument.getWaitingNumber());
//
//
//
//
//
//                });
//    }
//
//
//}

package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import com.team13.WaitDoc.hospital.repository.HospitalRepository;
import com.team13.WaitDoc.hospital.repository.OperatingTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatingTimeService {
    private final OperatingTimeRepository operatingTimeRepository;
    private final HospitalRepository hospitalRepository;

    public OperatingTime toEntity(HospitalXml.Item item){
        Hospital hospital = hospitalRepository.findByHpid(item.getHpid()).orElse(null);
        if(hospital != null && operatingTimeRepository.findByHospital(hospital).isEmpty())
            return OperatingTime.builder()
                    .hospital(hospital)
                    .monStartTime(intToTime(item.getDutyTime1s()))
                    .monEndTime(intToTime(item.getDutyTime1c()))
                    .tueStartTime(intToTime(item.getDutyTime2s()))
                    .tueEndTime(intToTime(item.getDutyTime2c()))
                    .wedStartTime(intToTime(item.getDutyTime3s()))
                    .wedEndTime(intToTime(item.getDutyTime3c()))
                    .thuStartTime(intToTime(item.getDutyTime4s()))
                    .thuEndTime(intToTime(item.getDutyTime4c()))
                    .friStartTime(intToTime(item.getDutyTime5s()))
                    .friEndTime(intToTime(item.getDutyTime5c()))
                    .satStartTime(intToTime(item.getDutyTime6s()))
                    .satEndTime(intToTime(item.getDutyTime6c()))
                    .sunStartTime(intToTime(item.getDutyTime7s()))
                    .sunEndTime(intToTime(item.getDutyTime7c()))
                    .holidayStartTime(intToTime(item.getDutyTime8s()))
                    .holidayEndTime(intToTime(item.getDutyTime8c()))
                    .build();
        return null;
    }

    public LocalTime intToTime(int time){
        int hour = time/100 > 23? time/100%24 : time/100;
        if(time == 0)
            return null;
        return LocalTime.of(hour, time % 100);

    }

    public void savaAll(List<OperatingTime> operatingTimes) {
        operatingTimeRepository.saveAll(operatingTimes);
    }
}

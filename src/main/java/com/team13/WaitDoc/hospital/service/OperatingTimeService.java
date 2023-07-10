package com.team13.WaitDoc.hospital.service;

import com.team13.WaitDoc.base.util.HospitalXml;
import com.team13.WaitDoc.hospital.entity.Hospital;
import com.team13.WaitDoc.hospital.entity.OperatingTime;
import com.team13.WaitDoc.hospital.repository.Hospital.HospitalRepository;
import com.team13.WaitDoc.hospital.repository.OperatingTime.OperatingTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
                    .nightDays(setNightDays(item))
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

    //야간 운영을 하는 요일을 저장
    private String setNightDays(HospitalXml.Item item) {
        StringBuilder sb = new StringBuilder();
        Map<String, int[]> daysMap = Map.of(
            "월", new int[] {item.getDutyTime1s(), item.getDutyTime1c()},
            "화", new int[] {item.getDutyTime2s(), item.getDutyTime2c()},
            "수", new int[] {item.getDutyTime3s(), item.getDutyTime3c()},
            "목", new int[] {item.getDutyTime4s(), item.getDutyTime4c()},
            "금", new int[] {item.getDutyTime5s(), item.getDutyTime5c()},
            "토", new int[] {item.getDutyTime6s(), item.getDutyTime6c()},
            "일", new int[] {item.getDutyTime7s(), item.getDutyTime7c()},
            "공휴일", new int[] {item.getDutyTime8s(), item.getDutyTime8c()}
            );
        for (Map.Entry<String, int[]> entry : daysMap.entrySet()) {
            String day = entry.getKey();
            int[] times = entry.getValue();
            if(compareTimes(times)){
                LocalTime time = intToTime(times[0]);
                sb.append(String.format("%s:%s시",
                        day,
                        time.getHour()));
                if(time.getMinute() > 0)
                    sb.append(time.getMinute()+"분");
                sb.append(" ");
            }

        }


        return sb.toString();
    }
    private Boolean compareTimes(int[] times){
        int open = times[0];
        int close = times[1];
        return (open != 0 && close != 0) &&
                ((open >= 2000 || (open > 0 && open <= 400)) ||
                (close >= 2000 || (close > 0 && close <= 400)));
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

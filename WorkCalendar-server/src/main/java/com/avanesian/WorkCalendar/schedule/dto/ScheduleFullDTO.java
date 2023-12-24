package com.avanesian.WorkCalendar.schedule.dto;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ScheduleFullDTO {

    private Long id;
    private EmployeeSafeDTO employee;
    private String dayType;
    private LocalDate dateSchedule;

    public ScheduleFullDTO(Long scId, Long emId, String emLn, String emFn, String email, String dayType, LocalDate date) {
        this.id = scId;
        this.employee = new EmployeeSafeDTO(emId, emFn, emLn, email);
        this.dayType = dayType;
        this.dateSchedule = date;
    };

}

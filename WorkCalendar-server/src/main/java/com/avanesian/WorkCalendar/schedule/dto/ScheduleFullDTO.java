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

}

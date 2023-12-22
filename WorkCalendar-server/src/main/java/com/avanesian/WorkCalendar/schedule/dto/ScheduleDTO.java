package com.avanesian.WorkCalendar.schedule.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ScheduleDTO {

    private Long id;
    private Long employeeId;
    private Long dayType;
    private LocalDate dateSchedule;

}

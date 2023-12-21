package com.avanesian.WorkCalendar.schedule.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ScheduleShortDTO {

    private Long id;
    private Long dayType;
    private LocalDate dateSchedule;

}

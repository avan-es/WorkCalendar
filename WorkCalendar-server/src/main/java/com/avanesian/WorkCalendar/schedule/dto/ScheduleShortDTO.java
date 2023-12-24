package com.avanesian.WorkCalendar.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ScheduleShortDTO {

    private String dayType;
    private LocalDate dateSchedule;

}

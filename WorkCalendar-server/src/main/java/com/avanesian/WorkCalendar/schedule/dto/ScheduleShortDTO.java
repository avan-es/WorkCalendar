package com.avanesian.WorkCalendar.schedule.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ScheduleShortDTO {

    private String dayType;
    private LocalDate date;

}

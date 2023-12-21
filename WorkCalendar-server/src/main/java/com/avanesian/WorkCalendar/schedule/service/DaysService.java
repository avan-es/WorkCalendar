package com.avanesian.WorkCalendar.schedule.service;


import com.avanesian.WorkCalendar.schedule.dto.DaysDto;
import com.avanesian.WorkCalendar.schedule.model.Days;

import java.util.List;

public interface DaysService {

    DaysDto addDayType(Days dayType);

    void deleteDayType(Long dayTypeId);

    List<DaysDto> getAllDaysType();
}

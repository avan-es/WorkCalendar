package com.avanesian.WorkCalendar.schedule.service;


import com.avanesian.WorkCalendar.schedule.model.Days;

import java.util.List;

public interface DaysService {

    Days addDayType(Days dayType);

    void deleteDayType(String dayType);

    List<Days> getAllDaysType();
}

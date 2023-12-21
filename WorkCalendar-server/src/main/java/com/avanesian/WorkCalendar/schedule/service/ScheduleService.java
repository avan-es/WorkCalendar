package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    ScheduleFullDTO addDayToSchedule (Schedule schedule);

    void deleteDayFromSchedule (Long scheduleId);

    ScheduleFullDTO updateDayToSchedule (ScheduleFullDTO schedule);

    ScheduleFullDTO getDayFromScheduleById (Long scheduleId);

    List<ScheduleShortDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to);

}

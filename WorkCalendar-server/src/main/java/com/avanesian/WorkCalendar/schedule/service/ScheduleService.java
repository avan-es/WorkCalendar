package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {

    ScheduleFullDTO addDayToSchedule (Schedule schedule);

    void deleteDayFromSchedule (Long scheduleId);

    ScheduleFullDTO updateDayToSchedule (ScheduleDTO schedule);

    ScheduleFullDTO getDayFromScheduleById (Long scheduleId);

    List<ScheduleDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to);

}

package com.avanesian.WorkCalendar.schedule.validation;

import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.repository.ScheduleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component("scheduleValidation")
@Slf4j
public class ScheduleValidation {

    @Autowired
    @Qualifier("dbScheduleRepository")
    private ScheduleRepository scheduleRepository;

    public Schedule getScheduleIfPresent(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);
        if (schedule == null) {
            log.error(String.format("Запись в расписании с ID %s не найдена.", scheduleId));
            throw new NotFoundException(String.format("Запись в расписании с ID %s не найдена.", scheduleId));
        }
        return schedule;
    }

    public boolean isSchedulePresent(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);
        return schedule != null;
    }

    public boolean isSchedulePresent(Long employeeId, LocalDate date) {
        return scheduleRepository.findScheduleByEmployeeAndDate(employeeId, date) != null;
    }
}

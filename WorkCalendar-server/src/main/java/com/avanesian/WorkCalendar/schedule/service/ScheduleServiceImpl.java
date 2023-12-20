package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import com.avanesian.WorkCalendar.schedule.repository.ScheduleRepository;
import com.avanesian.WorkCalendar.schedule.validation.DayValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{

    private final EmployeeRepository employeeRepository;
    private final DaysRepository daysRepository;
    private final ScheduleRepository scheduleRepository;
    private final DayValidation dayValidation;
    private final EmployeeValidation employeeValidation;

    @Override
    public ScheduleFullDTO addDayToSchedule(Schedule schedule) {
        checkSchedule(schedule);
        log.info(String.format("Добавлена запись в расписание сотрудника."));
        return scheduleRepository.save(schedule);
    }

    @Override
    public void deleteDayFromSchedule(Schedule schedule) {

    }

    @Override
    public ScheduleFullDTO updateDayToSchedule(ScheduleFullDTO schedule) {
        return null;
    }

    @Override
    public ScheduleFullDTO getDayFromScheduleById(Long scheduleId) {
        return null;
    }

    @Override
    public List<ScheduleShortDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to) {
        return null;
    }

    private void checkSchedule(Schedule schedule){
        employeeValidation.checkEmployeePresent(schedule.getEmployeeId());
        dayValidation.checkDayTypePresent(schedule.getDayType());
    }
}

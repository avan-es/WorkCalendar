package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleDTO;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.model.ScheduleMapper;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import com.avanesian.WorkCalendar.schedule.repository.ScheduleRepository;
import com.avanesian.WorkCalendar.schedule.validation.DayValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{


    private final ScheduleRepository scheduleRepository;
    private final DayValidation dayValidation;
    private final EmployeeValidation employeeValidation;
    private final EmployeeRepository employeeRepository;

    private final DaysRepository daysRepository;

    @Override
    public ScheduleFullDTO addDayToSchedule(Schedule schedule) {
        checkSchedule(schedule);
       // employeeRepository.findById(schedule.getEmployeeId());
        log.info(String.format("Добавлена запись в расписание."));
        Employee employee = employeeRepository.findEmployeeById(schedule.getEmployeeId());
        Days dayType = daysRepository.findDaysById(schedule.getDayType());
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scheduleRepository.save(schedule), employee, dayType);
    }

    @Override
    public void deleteDayFromSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
        log.info(String.format("Удалена запись из расписание."));
    }

    @Override
    public ScheduleFullDTO updateDayToSchedule(ScheduleDTO schedule) {
        Schedule scheduleForUpdate = scheduleRepository.findScheduleById(schedule.getId());
        Employee employee = employeeRepository.findEmployeeById(schedule.getEmployeeId());
        Days dayType = daysRepository.findDaysById(schedule.getDayType());
        scheduleForUpdate.setDateSchedule(schedule.getDateSchedule());
        scheduleForUpdate.setDayType(schedule.getDayType());
        scheduleForUpdate.setEmployeeId(schedule.getEmployeeId());
        log.info(String.format("Срока расписания с ID %s успешно обновлены.", scheduleForUpdate.getId()));
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scheduleRepository.save(scheduleForUpdate), employee, dayType);
    }

    @Override
    public ScheduleFullDTO getDayFromScheduleById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findScheduleById(scheduleId);
        Employee employee = employeeRepository.findEmployeeById(schedule.getEmployeeId());
        Days dayType = daysRepository.findDaysById(schedule.getDayType());
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(schedule, employee, dayType);
    }

    @Override
    public List<ScheduleDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to) {
        return scheduleRepository.getSchedulesByEmployeeIdAndDateScheduleBetween(employeeId, from, to).stream()
                .map(ScheduleMapper.INSTANT::scheduleToScheduleShortDTO)
                .collect(Collectors.toList());
    }

    private void checkSchedule(Schedule schedule){
        employeeValidation.isEmployeePresent(schedule.getEmployeeId());
        dayValidation.checkDayTypePresentById(schedule.getDayType());
    }
}

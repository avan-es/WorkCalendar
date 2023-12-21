package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{


    private final ScheduleRepository scheduleRepository;
    private final DayValidation dayValidation;
    private final EmployeeValidation employeeValidation;

    @Override
    public ScheduleFullDTO addDayToSchedule(Schedule schedule) {
        checkSchedule(schedule);
        log.info(String.format("Добавлена запись в расписание."));
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scheduleRepository.save(schedule));
    }

    @Override
    public void deleteDayFromSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
        log.info(String.format("Удалена запись из расписание."));
    }

    @Override
    public ScheduleFullDTO updateDayToSchedule(ScheduleFullDTO schedule) {
        Schedule scheduleForUpdate = scheduleRepository.findScheduleById(schedule.getId());
        scheduleForUpdate.setDateSchedule(schedule.getDateSchedule());
        scheduleForUpdate.setDayType(schedule.getDayType());
        scheduleForUpdate.setEmployeeId(schedule.getEmployeeId());
        log.info(String.format("Срока расписания с ID %s успешно обновлены.", scheduleForUpdate.getId()));
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scheduleRepository.save(scheduleForUpdate));
    }

    @Override
    public ScheduleFullDTO getDayFromScheduleById(Long scheduleId) {
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(
                scheduleRepository.findScheduleById(scheduleId));
    }

    @Override
    public List<ScheduleShortDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to) {
        return scheduleRepository.getSchedulesByEmployeeIdAndDateScheduleBetween(employeeId, from, to).stream()
                .map(ScheduleMapper.INSTANT::scheduleToScheduleShortDTO)
                .collect(Collectors.toList());
    }

    private void checkSchedule(Schedule schedule){
        employeeValidation.checkEmployeePresent(schedule.getEmployeeId());
        dayValidation.checkDayTypePresentById(schedule.getDayType());
    }
}

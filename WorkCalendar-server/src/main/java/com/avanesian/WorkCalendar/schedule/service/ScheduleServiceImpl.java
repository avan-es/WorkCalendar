package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.exeptions.ForbiddenException;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.model.ScheduleMapper;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import com.avanesian.WorkCalendar.schedule.repository.ScheduleRepository;
import com.avanesian.WorkCalendar.schedule.validation.DayValidation;
import com.avanesian.WorkCalendar.schedule.validation.ScheduleValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{


    private final ScheduleRepository scRepository;

    private final ScheduleValidation scValidation;
    private final DayValidation dayValidation;
    private final EmployeeValidation emValidation;
    private final EmployeeRepository emRepository;
    private final DaysRepository daysRepository;

    @Override
    public ScheduleFullDTO saveDayToSchedule(Schedule sc) {
        Employee em = emValidation.getEmployeeIfPresent(sc.getEmployeeId());
        Days dt = dayValidation.getDayTypeIfPresentById(sc.getDayType());
        if (scValidation.isSchedulePresent(sc.getEmployeeId(), sc.getDateSchedule())) {
            log.error(String.format(
                    "График на дату %s у сотрудника с ID %s уже создан. Воспользуйтесь обновлением.",
                    sc.getDateSchedule(), sc.getEmployeeId()));
            throw new ForbiddenException(String.format(
                    "График на дату %s у сотрудника с ID %s уже создан. Воспользуйтесь обновлением.",
                    sc.getDateSchedule(), sc.getEmployeeId()));
        }
        log.info(String.format(
                "Добавлена запись в расписание сотрудника с ID %d на дату %s.",
                sc.getEmployeeId(), sc.getDateSchedule()));
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scRepository.save(sc), em, dt);
    }

    @Override
    public void deleteDayFromSchedule(Long scheduleId) {
        scRepository.deleteById(scheduleId);
        log.info(String.format("Удалена запись с ID %d из расписание.", scheduleId));
    }

    @Override
    public ScheduleFullDTO updateDayToSchedule(Long scheduleID, String newDayType) {
        Schedule schedule = scValidation.getScheduleIfPresent(scheduleID);
        Employee employee = emValidation.getEmployeeIfPresent(schedule.getEmployeeId());
        Days day = dayValidation.getDayTypeIfPresentByName(newDayType);
        schedule.setDayType(day.getId());
        log.info(String.format("Срока расписания с ID %s успешно обновлена.", schedule.getId()));
        return ScheduleMapper.INSTANT.scheduleToScheduleFullDTO(scRepository.save(schedule), employee, day);
    }

    @Override
    public ScheduleFullDTO getDayFromScheduleById(Long scheduleId) {
        return scRepository.getScheduleFullDTOById(scheduleId);
    }

    @Override
    public List<ScheduleShortDTO> getEmployeeSchedule(Long employeeId, LocalDate from, LocalDate to) {
        return scRepository.getEmployeesScheduleShortDTO(employeeId, from, to);
    }

}

package com.avanesian.WorkCalendar.schedule.controller;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/schedule")
@RequiredArgsConstructor
public class ScheduleAdminController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleFullDTO saveDayToSchedule(@Valid @RequestBody Schedule schedule) {
        return scheduleService.saveDayToSchedule(schedule);
    }

    @PatchMapping("/{scheduleId}")
    public ScheduleFullDTO updateDayToSchedule(@PathVariable @PositiveOrZero Long scheduleId,
                                               @RequestParam String newDayType) {
        return scheduleService.updateDayToSchedule(scheduleId, newDayType);
    }

    @GetMapping
    public List<ScheduleShortDTO> getEmployeeSchedule(
            @RequestParam(name = "employeeId") Long employeeId,
            @RequestParam(name = "dateFrom") LocalDate dateFrom,
            @RequestParam(name = "dateTo") LocalDate dateTo) {
        return scheduleService.getEmployeeSchedule(employeeId, dateFrom, dateTo);
    }

    @DeleteMapping( "/{scheduleId}")
    public void deleteSchedule( @PathVariable Long scheduleId) {
        scheduleService.deleteDayFromSchedule(scheduleId);
    }

    @GetMapping( "/{scheduleId}")
    public ScheduleFullDTO getDayFromScheduleById(@PathVariable Long scheduleId) {
        return scheduleService.getDayFromScheduleById(scheduleId);
    }
}

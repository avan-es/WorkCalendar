package com.avanesian.WorkCalendar.schedule.controller;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/schedule")
@RequiredArgsConstructor
public class ScheduleAdminController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ScheduleFullDTO addSchedule(@Valid @RequestBody Schedule schedule) {
        return scheduleService.addDayToSchedule(schedule);
    }
}

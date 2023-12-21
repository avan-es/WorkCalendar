package com.avanesian.WorkCalendar.schedule.controller;

import com.avanesian.WorkCalendar.schedule.dto.DaysDto;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.DaysMapper;
import com.avanesian.WorkCalendar.schedule.service.DaysService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin/days")
@RequiredArgsConstructor
public class DaysAdminController {

    private final DaysService daysService;



    @PostMapping
    public DaysDto addDay(@Valid @RequestBody Days dayType) {
        DaysMapper.INSTANT.dayTypeToUpperCase(dayType);
        return daysService.addDayType(dayType);
    }

    @GetMapping
    public List<DaysDto> getAllDayTypes() {
        return daysService.getAllDaysType();
    }

    @DeleteMapping("/{dayId}")
    public void deleteDayType(@PathVariable Long dayId) {
        daysService.deleteDayType(dayId);
    }
}

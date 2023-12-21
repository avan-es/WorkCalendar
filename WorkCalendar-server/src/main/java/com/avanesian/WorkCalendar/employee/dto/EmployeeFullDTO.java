package com.avanesian.WorkCalendar.employee.dto;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EmployeeFullDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<ScheduleShortDTO> schedule = new ArrayList<>();

}

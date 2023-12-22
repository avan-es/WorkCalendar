package com.avanesian.WorkCalendar.employee.dto;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleDTO;
import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EmployeeFullDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private HashMap<String, List<ScheduleDTO>> schedule = new HashMap<>();
    private HashMap<String, List<TaskFullDTO>> tasks = new HashMap<>();

}

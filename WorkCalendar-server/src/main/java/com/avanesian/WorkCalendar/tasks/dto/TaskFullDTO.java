package com.avanesian.WorkCalendar.tasks.dto;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskFullDTO {

    private Long id;
    private String taskName;
    private String description;
    private String status;
    private Long duration;
    private LocalDate date;
    private EmployeeSafeDTO employee;

    TaskFullDTO(Long id, String taskName, String description, String status, Long duration, LocalDate date,
                Long emId, String firstName,  String lastName, String email) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.date = date;
        this.employee = new EmployeeSafeDTO(emId, firstName, lastName, email);
    }

}

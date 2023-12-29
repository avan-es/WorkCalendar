package com.avanesian.WorkCalendar.tasks.dto;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskShortDTO {
    private Long id;
    private String taskName;
    private String description;
    private String status;
    private Long duration;
    private LocalDate date;

    TaskShortDTO(Long id, String taskName, String description, String status, Long duration, LocalDate date) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
        this.status = status;
        this.duration = duration;
        this.date = date;
    }
}

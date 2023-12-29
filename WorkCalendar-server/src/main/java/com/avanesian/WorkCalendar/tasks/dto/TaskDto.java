package com.avanesian.WorkCalendar.tasks.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class TaskDto {
    private Long id;
    private String taskName;
    private String description;
    private String status;
    private Long duration;
    private LocalDate date;
    private Long employeeId;
}

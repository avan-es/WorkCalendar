package com.avanesian.WorkCalendar.tasks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_name", nullable = false)
    private String taskName;
    @Column(name = "description")
    private String description;
    @Column(name = "task_type", nullable = false)
    private Long status;
    @Column(name = "duration", nullable = false)
    @Max(480) @Min(1)
    private Long duration;
    @Column(name = "start_date", nullable = false)
    private LocalDate date;
    @Column(name = "employee", nullable = false)
    private Long employeeID;
}

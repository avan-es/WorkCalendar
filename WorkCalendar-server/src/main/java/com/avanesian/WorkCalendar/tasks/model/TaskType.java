package com.avanesian.WorkCalendar.tasks.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks_Type")
@Data
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "task_type", nullable = false)
    private String taskType;

}

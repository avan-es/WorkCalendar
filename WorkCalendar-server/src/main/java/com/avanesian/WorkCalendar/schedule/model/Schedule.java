package com.avanesian.WorkCalendar.schedule.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "schedule")
@Data
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "employee", nullable = false)
    private Long employeeId;
    @Column(name = "day_type", nullable = false)
    private Long dayType;
    @Column(name = "date_schedule", nullable = false)
    private LocalDate dateSchedule;

}

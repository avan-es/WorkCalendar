package com.avanesian.WorkCalendar.schedule.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "days")
@Data
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "day_type", nullable = false)
    private String dayType;

}

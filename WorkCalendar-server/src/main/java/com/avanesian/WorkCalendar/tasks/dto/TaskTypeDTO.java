package com.avanesian.WorkCalendar.tasks.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaskTypeDTO {

    private Long id;
    private String taskType;
}

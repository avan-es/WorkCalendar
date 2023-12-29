package com.avanesian.WorkCalendar.tasks.service;

import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;
import com.avanesian.WorkCalendar.tasks.dto.TaskDto;
import com.avanesian.WorkCalendar.tasks.dto.TaskShortDTO;
import com.avanesian.WorkCalendar.tasks.model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskService {

    TaskFullDTO saveTask(Task task);

    List<TaskShortDTO> getEmployeeTask(Long employeeId, LocalDate from, LocalDate to);

    TaskFullDTO getTask(Long taskId);

    TaskFullDTO updateTask(Long taskId, TaskDto task);

    void deleteTask(Long taskId);
}

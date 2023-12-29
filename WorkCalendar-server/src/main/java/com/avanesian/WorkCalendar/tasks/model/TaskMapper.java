package com.avanesian.WorkCalendar.tasks.model;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.model.EmployeeMapper;
import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;

public enum TaskMapper {
    INSTANT;

    public TaskFullDTO taskToTaskFullDTO (Task task, Employee employee, TaskType taskType) {
        TaskFullDTO taskFullDTO = new TaskFullDTO();
        taskFullDTO.setId(task.getId());
        taskFullDTO.setTaskName(task.getTaskName());
        taskFullDTO.setDescription(task.getDescription());
        taskFullDTO.setStatus(taskType.getTaskType());
        taskFullDTO.setDuration(task.getDuration());
        taskFullDTO.setDate(task.getDate());
        taskFullDTO.setEmployee(EmployeeMapper.INSTANT.toEmployeeSafeDto(employee));
        return taskFullDTO;
    }
}

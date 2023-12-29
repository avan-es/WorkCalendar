package com.avanesian.WorkCalendar.tasks.validation;

import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import com.avanesian.WorkCalendar.tasks.model.TaskType;
import com.avanesian.WorkCalendar.tasks.repository.TaskTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("taskTypeValidation")
@Slf4j
public class TaskTypeValidation {
    @Autowired
    @Qualifier("dbTaskTypeRepository")
    private TaskTypeRepository taskTypeRepository;

    public TaskType getTaskTypeIfPresent(Long taskTypeId) {
        TaskType taskType = taskTypeRepository.findTaskTypeById(taskTypeId);
        if (taskType == null) {
            log.error(String.format("Тип задачи с ID %s не найден.", taskTypeId));
            throw new NotFoundException(String.format("Тип задачи с ID %s не найден.", taskTypeId));
        }
        return taskType;
    }

    public TaskType getTaskTypeIfPresent(String taskTypeName) {
        TaskType taskType = taskTypeRepository.findTaskTypeByName(taskTypeName);
        if (taskType == null) {
            log.error(String.format("Тип задачи %s не найден.", taskTypeName));
            throw new NotFoundException(String.format("Тип задачи %s не найден.", taskTypeName));
        }
        return taskType;
    }

    public boolean isTaskTypePresent(Long taskTypeId) {
        TaskType taskType = taskTypeRepository.findTaskTypeById(taskTypeId);
        return taskType != null;
    }
}

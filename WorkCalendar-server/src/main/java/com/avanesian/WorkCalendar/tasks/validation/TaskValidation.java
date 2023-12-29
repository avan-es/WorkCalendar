package com.avanesian.WorkCalendar.tasks.validation;

import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import com.avanesian.WorkCalendar.tasks.model.Task;
import com.avanesian.WorkCalendar.tasks.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("taskValidation")
@Slf4j
public class TaskValidation {

    @Autowired
    @Qualifier("dbTaskRepository")
    private TaskRepository taskRepository;

    public Task getTaskIfPresent(Long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        if (task == null) {
            log.error(String.format("Задача с ID %s не найдена.", taskId));
            throw new NotFoundException(String.format("Задача с ID %s не найдена.", taskId));
        }
        return task;
    }

    public boolean isTaskPresent(Long taskId) {
        Task task = taskRepository.findTaskById(taskId);
        return task != null;
    }
}

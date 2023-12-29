package com.avanesian.WorkCalendar.tasks.repository;

import com.avanesian.WorkCalendar.tasks.model.TaskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component("dbTaskTypeRepository")
public interface TaskTypeRepository extends JpaRepository<TaskType, Long> {

    TaskType findTaskTypeById(Long id);

    @Query(value = "SELECT * " +
            "FROM tasks_type " +
            "WHERE UPPER(tasks_type.task_type) = UPPER(?1) " +
            "LIMIT 1", nativeQuery = true)
    TaskType findTaskTypeByName(String taskType);
}

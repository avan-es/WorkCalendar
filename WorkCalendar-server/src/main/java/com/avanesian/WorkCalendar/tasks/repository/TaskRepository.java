package com.avanesian.WorkCalendar.tasks.repository;

import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;
import com.avanesian.WorkCalendar.tasks.dto.TaskShortDTO;
import com.avanesian.WorkCalendar.tasks.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component("dbTaskRepository")
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskById(Long taskId);

    @Query(value = "SELECT SUM(duration) " +
            "FROM tasks " +
            "WHERE employee = ?1 " +
            "AND start_Date = ?2 ", nativeQuery = true)
    Long countMinutes(Long employeeID, LocalDate date);

    @Query("SELECT new com.avanesian.WorkCalendar.tasks.dto.TaskShortDTO(t.id,  t.taskName, t.description, tt.taskType, t.duration," +
            "t.date) " +
    "FROM Task AS t " +
            "JOIN Employee AS em ON t.employeeID = em.id " +
            "JOIN TaskType AS tt ON t.status = tt.id " +
            "WHERE t.employeeID = ?1 " +
            "AND t.date BETWEEN ?2 AND ?3 " +
            "ORDER BY t.date")
    List<TaskShortDTO> getEmployeeTasks(Long employeeId, LocalDate from, LocalDate to);

}

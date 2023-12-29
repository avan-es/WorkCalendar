package com.avanesian.WorkCalendar.tasks.controller;

import com.avanesian.WorkCalendar.tasks.dto.TaskDto;
import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;
import com.avanesian.WorkCalendar.tasks.dto.TaskShortDTO;
import com.avanesian.WorkCalendar.tasks.model.Task;
import com.avanesian.WorkCalendar.tasks.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/tasks")
@RequiredArgsConstructor
public class TaskAdminController {

    private final TaskService taskService;

    @PostMapping
    public TaskFullDTO saveTask(@Valid @RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping
    public List<TaskShortDTO> getEmployeeTask(@RequestParam(name = "employeeId") Long employeeId,
                                              @RequestParam(name = "dateFrom") LocalDate dateFrom,
                                              @RequestParam(name = "dateTo") LocalDate dateTo) {
        return taskService.getEmployeeTask(employeeId, dateFrom, dateTo);
    }

    @GetMapping("/{taskId}")
    public TaskFullDTO getTask(@PathVariable Long taskId) {
        return taskService.getTask(taskId);
    }

    @DeleteMapping("/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }

    @PatchMapping("/{taskId}")
    public TaskFullDTO updateTask(@PathVariable @PositiveOrZero Long taskId,
                                  @RequestBody TaskDto task) {
        return taskService.updateTask(taskId, task);
    }
}

package com.avanesian.WorkCalendar.tasks.service;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.exeptions.ForbiddenException;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import com.avanesian.WorkCalendar.schedule.repository.ScheduleRepository;
import com.avanesian.WorkCalendar.schedule.validation.DayValidation;
import com.avanesian.WorkCalendar.schedule.validation.ScheduleValidation;
import com.avanesian.WorkCalendar.tasks.dto.TaskFullDTO;
import com.avanesian.WorkCalendar.tasks.dto.TaskDto;
import com.avanesian.WorkCalendar.tasks.dto.TaskShortDTO;
import com.avanesian.WorkCalendar.tasks.model.Task;
import com.avanesian.WorkCalendar.tasks.model.TaskMapper;
import com.avanesian.WorkCalendar.tasks.model.TaskType;
import com.avanesian.WorkCalendar.tasks.repository.TaskRepository;
import com.avanesian.WorkCalendar.tasks.repository.TaskTypeRepository;
import com.avanesian.WorkCalendar.tasks.validation.TaskTypeValidation;
import com.avanesian.WorkCalendar.tasks.validation.TaskValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.avanesian.WorkCalendar.constants.Constants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class TaskServiceIml implements TaskService{
    private final ScheduleRepository scRepository;

    private final ScheduleValidation scValidation;
    private final DayValidation dayValidation;
    private final EmployeeValidation emValidation;
    private final EmployeeRepository emRepository;
    private final DaysRepository daysRepository;

    private final TaskRepository taskRepository;
    private final TaskValidation taskValidation;

    private final TaskTypeRepository taskTypeRepository;
    private final TaskTypeValidation taskTypeValidation;

    @Override
    public TaskFullDTO saveTask(Task task) {
        Employee em = emValidation.getEmployeeIfPresent(task.getEmployeeID());
        TaskType taskType = taskTypeValidation.getTaskTypeIfPresent(task.getStatus());
        Schedule schedule = scRepository.findScheduleByEmployeeAndDate(em.getId(), task.getDate());
        Days day = daysRepository.findDaysById(schedule.getDayType());
        checkIfEmployeeHaveTimeForThisTask(task.getEmployeeID(), task.getDate(), task.getDuration(), day.getDayType());
        return TaskMapper.INSTANT.taskToTaskFullDTO(taskRepository.save(task), em, taskType);
    }

    @Override
    public List<TaskShortDTO> getEmployeeTask(Long employeeId, LocalDate from, LocalDate to) {
        emValidation.isEmployeePresent(employeeId);
        return taskRepository.getEmployeeTasks(employeeId, from, to);
    }

    @Override
    public TaskFullDTO getTask(Long taskId) {
        Task task = taskValidation.getTaskIfPresent(taskId);
        Employee employee = emValidation.getEmployeeIfPresent(task.getEmployeeID());
        TaskType taskType = taskTypeValidation.getTaskTypeIfPresent(task.getStatus());
        return TaskMapper.INSTANT.taskToTaskFullDTO(task,employee,taskType);
    }

    @Override
    public TaskFullDTO updateTask(Long taskId, TaskDto task) {
        TaskType taskType = new TaskType();
        Employee employee = new Employee();
        Task taskForUpdate = taskValidation.getTaskIfPresent(taskId);
        if (task.getTaskName() != null) {
            taskForUpdate.setTaskName(task.getTaskName());
        }
        if (task.getDescription() != null) {
            taskForUpdate.setDescription(task.getDescription());
        }
        if (task.getStatus() != null) {
            taskType = taskTypeValidation.getTaskTypeIfPresent(task.getStatus());
            taskForUpdate.setStatus(taskType.getId());
        } else {
            taskType = taskTypeValidation.getTaskTypeIfPresent(taskForUpdate.getStatus());
        }
        if (task.getDuration() != null) {
            taskForUpdate.setDuration(task.getDuration());
        }
        if (task.getDate() != null) {
            taskForUpdate.setDate(task.getDate());
        }
        if (task.getEmployeeId() != null) {
            employee = emValidation.getEmployeeIfPresent(task.getEmployeeId());
            taskForUpdate.setEmployeeID(task.getEmployeeId());
        } else {
            employee = emValidation.getEmployeeIfPresent(taskForUpdate.getEmployeeID());
        }
        checkIfEmployeeHaveTimeForThisTask(taskForUpdate.getEmployeeID(), taskForUpdate.getDate(), taskForUpdate.getDuration(), taskType.getTaskType());
        return TaskMapper.INSTANT.taskToTaskFullDTO(taskRepository.save(taskForUpdate), employee, taskType);
    }

    @Override
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private void checkIfEmployeeHaveTimeForThisTask(Long employeeId, LocalDate date, Long duration, String dayType) {
        long minutesLeft = TOTAL_WORK_MINUTES;
        Long baseMinutes = taskRepository.countMinutes(employeeId, date);
        if ( baseMinutes != null) {
            minutesLeft -=baseMinutes;
        }
        if (minutesLeft < duration || dayType.equals("Рабочий")) {
            throw new ForbiddenException(String.format(
                    "У работника с ID %s не хватит рабочего времени на выполнение данной задачи. " +
                            "Или данный день не является рабочим." +
                            "Осталось свободных рабочих минут на выбранную дату: %d.",
                    employeeId, minutesLeft));
        }
    }
}

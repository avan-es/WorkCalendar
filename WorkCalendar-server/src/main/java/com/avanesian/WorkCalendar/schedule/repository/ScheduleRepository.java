package com.avanesian.WorkCalendar.schedule.repository;

import com.avanesian.WorkCalendar.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbScheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getSchedulesByEmployeeId (Long employeeId);

}

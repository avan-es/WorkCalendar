package com.avanesian.WorkCalendar.schedule.repository;

import com.avanesian.WorkCalendar.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component("dbScheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getSchedulesByEmployeeIdAndDateScheduleBetween (Long employeeId, LocalDate from, LocalDate to);

    Schedule findScheduleById (Long scheduleId);

}

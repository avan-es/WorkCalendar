package com.avanesian.WorkCalendar.schedule.repository;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;
import com.avanesian.WorkCalendar.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component("dbScheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> getSchedulesByEmployeeIdAndDateScheduleBetween (Long employeeId, LocalDate from, LocalDate to);

    Schedule findScheduleById (Long scheduleId);


    @Query("SELECT new com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO(d.dayType, sc.dateSchedule) " +
            "FROM Schedule AS sc " +
            "JOIN Days AS d ON sc.dayType = d.id " +
            "WHERE sc.employeeId = ?1 " +
            "AND sc.dateSchedule BETWEEN ?2 AND ?3 " +
            "ORDER BY sc.dateSchedule ")
    List<ScheduleShortDTO> getEmployeesScheduleShortDTO (Long employeeId, LocalDate from, LocalDate to);

    @Query(value = "SELECT * " +
            "FROM Schedule sc " +
            "WHERE sc.employee = ?1 " +
            "AND sc.date_schedule = ?2 " +
            "ORDER BY sc.date_schedule DESC LIMIT 1 ", nativeQuery = true)
    Schedule findScheduleByEmployeeAndDate(Long employeeId, LocalDate date);

    @Query("SELECT new com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO(sc.id, em.id, em.firstName, em.lastName, em.email, " +
            "d.dayType, sc.dateSchedule) " +
            "FROM Schedule AS sc " +
            "JOIN Employee AS em ON sc.employeeId = em.id " +
            "JOIN Days AS d ON sc.dayType = d.id " +
            "WHERE sc.id = ?1 ")
    ScheduleFullDTO getScheduleFullDTOById(Long scheduleId);

}

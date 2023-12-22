package com.avanesian.WorkCalendar.schedule.model;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.model.EmployeeMapper;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleDTO;


public enum ScheduleMapper {
    INSTANT;

   public ScheduleFullDTO scheduleToScheduleFullDTO(Schedule schedule, Employee employee, Days dayType) {
       ScheduleFullDTO scheduleFullDTO = new ScheduleFullDTO();
       scheduleFullDTO.setId(schedule.getId());
       scheduleFullDTO.setEmployee(EmployeeMapper.INSTANT.toEmployeeSafeDto(employee));
       scheduleFullDTO.setDayType(dayType.getDayType());
       scheduleFullDTO.setDateSchedule(schedule.getDateSchedule());
       return scheduleFullDTO;
   }

   public ScheduleDTO scheduleToScheduleShortDTO(Schedule schedule) {
       ScheduleDTO scheduleShortDTO = new ScheduleDTO();
       scheduleShortDTO.setId(schedule.getId());
       scheduleShortDTO.setDayType(schedule.getDayType());
       scheduleShortDTO.setDateSchedule(schedule.getDateSchedule());
       return scheduleShortDTO;
   }
}

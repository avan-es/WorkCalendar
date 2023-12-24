package com.avanesian.WorkCalendar.schedule.model;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.model.EmployeeMapper;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

   public ScheduleDTO scheduleToScheduleDTO(Schedule schedule) {
       ScheduleDTO scheduleDTO = new ScheduleDTO();
       scheduleDTO.setId(schedule.getId());
       scheduleDTO.setDayType(schedule.getDayType());
       scheduleDTO.setDateSchedule(schedule.getDateSchedule());
       return scheduleDTO;
   }

    public List<ScheduleShortDTO> scheduleToScheduleShortDTO(List<Schedule> schedule, HashMap<Long, String> daysType) {
        List<ScheduleShortDTO> result = new ArrayList<>();
        for (Schedule sc: schedule) {
            ScheduleShortDTO temp = new ScheduleShortDTO();
            temp.setDayType(daysType.get(sc.getDayType()));
            temp.setDateSchedule(sc.getDateSchedule());
            result.add(temp);
        }
        return result;
    }
}

package com.avanesian.WorkCalendar.schedule.model;

import com.avanesian.WorkCalendar.schedule.dto.ScheduleFullDTO;
import com.avanesian.WorkCalendar.schedule.dto.ScheduleShortDTO;


public enum ScheduleMapper {
    INSTANT;

   public ScheduleFullDTO scheduleToScheduleFullDTO(Schedule schedule) {
       ScheduleFullDTO scheduleFullDTO = new ScheduleFullDTO();
       scheduleFullDTO.setId(schedule.getId());
       scheduleFullDTO.setEmployeeId(schedule.getEmployeeId());
       scheduleFullDTO.setDayType(schedule.getDayType());
       scheduleFullDTO.setDateSchedule(schedule.getDateSchedule());
       return scheduleFullDTO;
   }

   public ScheduleShortDTO scheduleToScheduleShortDTO(Schedule schedule) {
       ScheduleShortDTO scheduleShortDTO = new ScheduleShortDTO();
       scheduleShortDTO.setId(schedule.getId());
       scheduleShortDTO.setDayType(schedule.getDayType());
       scheduleShortDTO.setDateSchedule(schedule.getDateSchedule());
       return scheduleShortDTO;
   }
}

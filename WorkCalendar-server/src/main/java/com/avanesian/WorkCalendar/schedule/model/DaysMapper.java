package com.avanesian.WorkCalendar.schedule.model;

import com.avanesian.WorkCalendar.enums.DaysType;
import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import com.avanesian.WorkCalendar.schedule.dto.DaysDto;


public enum DaysMapper {
    INSTANT;

    public void dayTypeToUpperCase(Days day) {
        day.setDayType(day.getDayType().toUpperCase());
    }

    public DaysDto daysToDaysDto (Days day) {
        DaysDto daysDto = new DaysDto();
        daysDto.setId(day.getId());
        daysDto.setDayType(day.getDayType());
        return daysDto;
    }
    public String dayTypeEnumToString(DaysType daysType) {
        switch (daysType) {
            case WORK -> {
                return "Рабочий";
            }
            case DAY_OFF -> {
                return "Выходной";
            }
            case VACATION -> {
                return "Отпуск";
            }
            case SICK_LEAVE -> {
                return "Больничный";
            }
            default -> throw new NotFoundException(String.format("Тип дня %s не найден.", daysType));
        }
    }

}

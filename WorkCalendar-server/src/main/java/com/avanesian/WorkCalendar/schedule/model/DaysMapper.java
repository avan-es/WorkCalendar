package com.avanesian.WorkCalendar.schedule.model;

import com.avanesian.WorkCalendar.enums.DaysType;
import com.avanesian.WorkCalendar.exeptions.NotFoundException;

import java.util.Locale;

import static com.avanesian.WorkCalendar.enums.DaysType.*;

public enum DaysMapper {
    INSTANT;

    public void dayTypeToUpperCase(Days day) {
        day.setDayType(day.getDayType().toUpperCase());
    }

    public String dayTypeEnumToString(DaysType daysType) {
        switch (daysType) {
            case WORK -> {
                return "WORK";
            }
            case DAY_OFF -> {
                return "DAY_OFF";
            }
            case HOLIDAYS -> {
                return "HOLIDAYS";
            }
            case VACATION -> {
                return "VACATION";
            }
            case SICK_LEAVE -> {
                return "SICK_LEAVE";
            }
            default -> throw new NotFoundException(String.format("Тип дня %s не найден.", daysType));
        }
    }

    public DaysType dayTypeStringToEnum(String daysType) {
        switch (daysType.toUpperCase(Locale.ROOT)) {
            case "WORK" -> {
                return WORK;
            }
            case "DAY_OFF" -> {
                return DAY_OFF;
            }
            case "HOLIDAYS" -> {
                return HOLIDAYS;
            }
            case "VACATION" -> {
                return VACATION;
            }
            case "SICK_LEAVE" -> {
                return SICK_LEAVE;
            }
            default -> throw new NotFoundException(String.format("Тип дня %s не найден.", daysType));
        }
    }
}

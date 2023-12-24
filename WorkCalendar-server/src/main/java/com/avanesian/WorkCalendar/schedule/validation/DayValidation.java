package com.avanesian.WorkCalendar.schedule.validation;

import com.avanesian.WorkCalendar.enums.DaysType;
import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("dayValidation")
@Slf4j
public class DayValidation {

    @Autowired
    @Qualifier("dbDaysRepository")
    private DaysRepository daysRepository;

    public Days getDayTypeIfPresent(DaysType type) {
        Days day = daysRepository.getDayByDayTypeName(type.name());
        if (day == null) {
            log.error(String.format("Тип дня %s не существует.", type));
            throw new NotFoundException(String.format("Тип дня %s не существует.", type));
        }
        return day;
    }

    public boolean isDayTypeNameIsFree(String dayType) {
        Days days = daysRepository.getDayByDayTypeName(dayType);
        if (days != null) {
            log.error(String.format("Тип дня %s уже существует.", dayType));
            throw new NotFoundException(String.format("Тип дня %s уже существует.", dayType));
        }
        return true;
    }

    public boolean isDayTypePresentById(Long id) {
        Days days = daysRepository.findDaysById(id);
        if (days == null) {
            log.error(String.format("Тип дня с ID %d не найден.", id));
            throw new NotFoundException(String.format("Тип дня с ID %d не найден.", id));
        }
        return true;
    }

    public Days getDayTypeIfPresentById(Long id) {
        Days day = daysRepository.findDaysById(id);
        if (day == null) {
            log.error(String.format("Тип дня с ID %d не найден.", id));
            throw new NotFoundException(String.format("Тип дня с ID %d не найден.", id));
        }
        return day;
    }

    public Days getDayTypeIfPresentByName(String dayType) {
        Days day = daysRepository.getDayByDayTypeName(dayType);
        if (day == null) {
            log.error(String.format("Тип дня \"%s\" не найден.", dayType));
            throw new NotFoundException(String.format("Тип дня \"%s\" не найден.", dayType));
        }
        return day;
    }

}
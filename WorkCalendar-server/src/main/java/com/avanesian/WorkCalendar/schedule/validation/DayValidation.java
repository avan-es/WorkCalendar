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
        Days day = daysRepository.findDaysByDayType(type.name());
        if (day == null) {
            log.error(String.format("Тип дня %s не существует.", type));
            throw new NotFoundException(String.format("Тип дня %s не существует.", type));
        }
        return day;
    }

    public void checkDayTypePresentByString(String dayType) {
        Days days = daysRepository.findDaysByDayType(dayType);
        if (days != null) {
            throw new NotFoundException(String.format("Тип дня %d уже существует.", dayType));
        }
    }

    public void checkDayTypePresentById(Long id) {
        Days days = daysRepository.findDaysById(id);
        if (days == null) {
            throw new NotFoundException(String.format("Тип дня с ID %d не найден.", id));
        }
    }
}

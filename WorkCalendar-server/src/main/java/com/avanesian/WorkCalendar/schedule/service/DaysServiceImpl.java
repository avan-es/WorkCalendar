package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.DaysMapper;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DaysServiceImpl implements DaysService {

    private final DaysRepository daysRepository;

    @Override
    public Days addDayType(Days dayType) {
        DaysMapper.INSTANT.dayTypeToUpperCase(dayType);
        log.info(String.format("Добавление типа дня %S.", dayType.getDayType()));
        return daysRepository.save(dayType);
    }

    @Override
    public void deleteDayType(String dayType) {
        log.info(String.format("Удаление типа дня %S.", dayType.toUpperCase()));
        daysRepository.deleteByDayType(dayType.toUpperCase());
    }

    @Override
    public List<Days> getAllDaysType() {
        return daysRepository.findAll();
    }
}

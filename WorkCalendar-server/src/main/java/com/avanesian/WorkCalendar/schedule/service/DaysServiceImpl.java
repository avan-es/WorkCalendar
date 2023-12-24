package com.avanesian.WorkCalendar.schedule.service;

import com.avanesian.WorkCalendar.schedule.dto.DaysDto;
import com.avanesian.WorkCalendar.schedule.model.Days;
import com.avanesian.WorkCalendar.schedule.model.DaysMapper;
import com.avanesian.WorkCalendar.schedule.repository.DaysRepository;
import com.avanesian.WorkCalendar.schedule.validation.DayValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DaysServiceImpl implements DaysService {

    private final DaysRepository daysRepository;
    private final DayValidation dayValidation;

    @Override
    public DaysDto addDayType(Days dayType) {
        DaysMapper.INSTANT.dayTypeToUpperCase(dayType);
        dayValidation.isDayTypeNameIsFree(dayType.getDayType());
        log.info(String.format("Добавление типа дня %S.", dayType.getDayType()));
        return DaysMapper.INSTANT.daysToDaysDto(
                daysRepository.save(dayType));
    }

    @Override
    public void deleteDayType(Long dayTypeId) {
        log.info(String.format("Удаление типа дня с ID %S.", dayTypeId));
        daysRepository.deleteById(dayTypeId);
    }

    @Override
    public List<DaysDto> getAllDaysType() {
        return daysRepository.findAll().stream()
                .map(DaysMapper.INSTANT::daysToDaysDto)
                .collect(Collectors.toList());
    }
}

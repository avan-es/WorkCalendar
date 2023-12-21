package com.avanesian.WorkCalendar.schedule.repository;

import com.avanesian.WorkCalendar.schedule.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbDaysRepository")
public interface DaysRepository extends JpaRepository<Days, Long> {

    Days findDaysByDayType(String dayType);

    void deleteById (Long dayTypeId);

    List<Days> findAll();

    Days findDaysById (Long id);

}

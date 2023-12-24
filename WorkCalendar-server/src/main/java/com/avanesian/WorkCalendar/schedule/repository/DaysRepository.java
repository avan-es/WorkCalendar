package com.avanesian.WorkCalendar.schedule.repository;

import com.avanesian.WorkCalendar.schedule.model.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbDaysRepository")
public interface DaysRepository extends JpaRepository<Days, Long> {

    @Query(value = "SELECT * " +
            "FROM days " +
            "WHERE UPPER(day_type) = UPPER(?1) " +
            "LIMIT 1", nativeQuery = true)
    Days getDayByDayTypeName(String dayType);

    void deleteById (Long dayTypeId);

    List<Days> findAll();

    Days findDaysById (Long id);

    List<Days> findDaysByIdIn(List<Long> daysId);

}

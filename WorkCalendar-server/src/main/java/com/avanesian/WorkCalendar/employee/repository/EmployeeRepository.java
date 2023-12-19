package com.avanesian.WorkCalendar.employee.repository;

import com.avanesian.WorkCalendar.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("dbEmployeeRepository")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee getUserByEmail(String email);

    Employee getUserById(Long id);

    List<Employee> getAll();
}

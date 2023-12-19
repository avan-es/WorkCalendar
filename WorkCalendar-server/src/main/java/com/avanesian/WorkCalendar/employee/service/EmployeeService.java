package com.avanesian.WorkCalendar.employee.service;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;
import com.avanesian.WorkCalendar.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeSafeDTO addEmployee(Employee employee);

    EmployeeSafeDTO getEmployeeByEmail(String email);

    void deleteEmployee (Long id);

    List<EmployeeSafeDTO> getAllEmployees();

    EmployeeSafeDTO updateEmployee (EmployeeWhitPasswordDTO employee);

    EmployeeSafeDTO getEmployeeById(Long id);


}

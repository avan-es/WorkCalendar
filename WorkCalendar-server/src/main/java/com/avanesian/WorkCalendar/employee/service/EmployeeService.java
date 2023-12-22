package com.avanesian.WorkCalendar.employee.service;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;
import com.avanesian.WorkCalendar.employee.model.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeSafeDTO saveEmployee(Employee employee);

    void deleteEmployeeById(Long id);

    List<EmployeeSafeDTO> getAllEmployees();

    EmployeeSafeDTO updateEmployee (EmployeeWhitPasswordDTO employee);

    EmployeeSafeDTO getEmployeeById(Long id);


}

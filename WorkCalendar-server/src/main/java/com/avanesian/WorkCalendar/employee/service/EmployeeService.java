package com.avanesian.WorkCalendar.employee.service;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.model.Employee;

public interface EmployeeService {

    EmployeeSafeDTO addUser(Employee employee);


}

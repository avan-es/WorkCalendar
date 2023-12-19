package com.avanesian.WorkCalendar.employee.model;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;

public enum EmployeeMapper {
    INSTANT;

    public EmployeeSafeDTO toEmployeeSafeDto(Employee employee) {
        EmployeeSafeDTO employeeSafeDTO = new EmployeeSafeDTO();
        employeeSafeDTO.setId(employee.getId());
        employeeSafeDTO.setFirstName(employee.getFirstName());
        employeeSafeDTO.setLastName(employee.getLastName());
        employeeSafeDTO.setEmail(employee.getEmail());
        return employeeSafeDTO;
    }

    public EmployeeWhitPasswordDTO toEmployeeWithPasswordDto (Employee employee) {
        EmployeeWhitPasswordDTO employeeWhitPasswordDTO = new EmployeeWhitPasswordDTO();
        employeeWhitPasswordDTO.setId(employee.getId());
        employeeWhitPasswordDTO.setFirstName(employee.getFirstName());
        employeeWhitPasswordDTO.setLastName(employee.getLastName());
        employeeWhitPasswordDTO.setEmail(employee.getEmail());
        employeeWhitPasswordDTO.setUserPassword(employee.getUserPassword());
        return employeeWhitPasswordDTO;
    }

    public Employee toEmployee(EmployeeWhitPasswordDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setUserPassword(employeeDTO.getUserPassword());
        return employee;
    }
}

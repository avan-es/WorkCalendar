package com.avanesian.WorkCalendar.employee.model;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;

public enum EmployeeMapper {
    INSTANT;

    public EmployeeSafeDTO toUserSafeDto(Employee employee) {
        return EmployeeSafeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .build();
    }

    public EmployeeWhitPasswordDTO toUserWithPasswordDto (Employee employee) {
        return EmployeeWhitPasswordDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .userPassword(employee.getUserPassword())
                .build();
    }
}

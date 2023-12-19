package com.avanesian.WorkCalendar.employee.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
public class EmployeeWhitPasswordDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String userPassword;

}

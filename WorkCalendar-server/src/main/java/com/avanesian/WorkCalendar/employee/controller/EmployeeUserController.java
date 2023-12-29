package com.avanesian.WorkCalendar.employee.controller;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;
import com.avanesian.WorkCalendar.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class EmployeeUserController {

    private final EmployeeService employeeService;

    @PatchMapping
    public EmployeeSafeDTO updateEmployee(@RequestHeader (value = "User-Id") Long employeeId,
                                          @RequestBody EmployeeWhitPasswordDTO employeeWhitPasswordDTO) {
        employeeWhitPasswordDTO.setId(employeeId);
        return employeeService.updateEmployee(employeeWhitPasswordDTO);
    }

    @GetMapping
    public EmployeeSafeDTO getEmployee(@RequestHeader (value = "User-Id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

}

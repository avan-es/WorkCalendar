package com.avanesian.WorkCalendar.employee.controller;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;
import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {

    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeSafeDTO addEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @GetMapping
    public List<EmployeeSafeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PatchMapping("/{employeeId}")
    public EmployeeSafeDTO updateEmployee(@RequestBody EmployeeWhitPasswordDTO employeeWhitPasswordDTO,
                              @PathVariable Long employeeId) {
        employeeWhitPasswordDTO.setId(employeeId);
        return employeeService.updateEmployee(employeeWhitPasswordDTO);
    }

    @GetMapping("/{employeeId}")
    public EmployeeSafeDTO getEmployee(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteEmployee(employeeId);
    }
}

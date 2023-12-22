package com.avanesian.WorkCalendar.employee.service;

import com.avanesian.WorkCalendar.employee.dto.EmployeeSafeDTO;
import com.avanesian.WorkCalendar.employee.dto.EmployeeWhitPasswordDTO;
import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.model.EmployeeMapper;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.employee.validation.EmployeeValidation;
import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeValidation employeeValidation;



    @Override
    public EmployeeSafeDTO saveEmployee(Employee employee) {
        employeeValidation.isEmailValid(employee);
        log.info(String.format("Сотрудник с ID %s и почтой %S успешно создан.", employee.getId(), employee.getEmail()));
        return EmployeeMapper.INSTANT.toEmployeeSafeDto(
                employeeRepository.save(employee));
    }


    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        log.info(String.format("Сотрудник с ID %s удалён.", id));
    }

    @Override
    public List<EmployeeSafeDTO> getAllEmployees() {
        log.info("Получен список пользователей.");
        return employeeRepository.findAll().stream()
                .map(EmployeeMapper.INSTANT::toEmployeeSafeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeSafeDTO updateEmployee(EmployeeWhitPasswordDTO employee) {
        employeeValidation.emailValidationForExistUser(EmployeeMapper.INSTANT.toEmployee(employee));
        Employee employeeForUpdate = employeeRepository.findEmployeeById(employee.getId());
        if (employee.getFirstName() != null) {
            employeeForUpdate.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null) {
            employeeForUpdate.setLastName(employee.getLastName());
        }
        if (employee.getEmail() != null) {
            employeeForUpdate.setEmail(employee.getEmail());
        }
        if (employee.getUserPassword() != null) {
            employeeForUpdate.setUserPassword(employee.getUserPassword());
        }
        log.info(String.format("Данные сотрудника с ID %s успешно обновлены.", employeeForUpdate.getId()));
        return EmployeeMapper.INSTANT.toEmployeeSafeDto(employeeRepository.save(employeeForUpdate));
    }

    @Override
    public EmployeeSafeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new NotFoundException(String.format("Сотрудник с ID %s не найден.", id));
        }
        return EmployeeMapper.INSTANT.toEmployeeSafeDto(employee);
    }
}

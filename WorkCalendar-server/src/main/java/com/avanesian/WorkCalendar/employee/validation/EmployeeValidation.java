package com.avanesian.WorkCalendar.employee.validation;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.exeptions.ModelConflictException;
import com.avanesian.WorkCalendar.exeptions.ModelValidationException;
import com.avanesian.WorkCalendar.exeptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.regex.Pattern;

@Component("employeeValidation")
@Slf4j
public class EmployeeValidation {

    @Autowired
    @Qualifier("dbEmployeeRepository")
    private EmployeeRepository employeeRepository;

    private Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private boolean isEmailValid(final String hex) {
        return emailPattern.matcher(hex).matches();
    }

    public boolean isEmailValid(Employee employee) {
        if (!isEmailValid(employee.getEmail())) {
            log.error(String.format("Сотрудник не создан. Ошибка в адресе почты: %s.", employee.getEmail()));
            throw new ModelValidationException(String.format("Почтовый адрес '%s' не может быть использован.",
                    employee.getEmail()));
        }
        return true;
    }


    public void emailValidationForExistUser(Employee employee) {
        if (employee.getEmail() != null) {
            isEmailValid(employee);
            if (employeeRepository.findEmployeeByEmail(employee.getEmail()) != null) {
                Employee employeeForUpdate = employeeRepository.findEmployeeById(employee.getId());
                if (!employeeForUpdate.getEmail().equals(employee.getEmail())) {
                    isEmailFree(employee);
                }
            }
        }
    }

    private boolean isEmailFree(Employee employee) {
        if (employeeRepository.findEmployeeByEmail(employee.getEmail()) != null) {
            log.error(String.format("Сотрудник не создан. Почта %s уже занята.", employee.getEmail()));
            throw new ModelConflictException(String.format("Почтовый адрес '%s' уже занят.",
                    employee.getEmail()));
        }
        return true;
    }

    public boolean isEmployeePresent(Long employeeId) {
        Employee employee = employeeRepository.findEmployeeById(employeeId);
        if (employee == null) {
            throw new NotFoundException(String.format("Сотрудник с ID %d не найден.", employeeId));
        }
        return true;
    }
}

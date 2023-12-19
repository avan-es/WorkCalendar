package com.avanesian.WorkCalendar.employee.validation;

import com.avanesian.WorkCalendar.employee.model.Employee;
import com.avanesian.WorkCalendar.employee.repository.EmployeeRepository;
import com.avanesian.WorkCalendar.exeptions.ModelValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("employeeValidation")
@Slf4j
public class EmployeeValidation {

    @Autowired
    @Qualifier("dbEmployeeRepository")
    private EmployeeRepository EmployeeRepository;

    private Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

    private boolean isEmailValid(final String hex) {
        return emailPattern.matcher(hex).matches();
    }

    public void checkEmail(Employee employee) {
        if (!isEmailValid(employee.getEmail())) {
            log.error(String.format("Пользователь не создан. Ошибка в адресе почты: %s.", employee.getEmail()));
            throw new ModelValidationException(String.format("Почтовый адрес '%s' не может быть использован.",
                    employee.getEmail()));
        }
    }

}

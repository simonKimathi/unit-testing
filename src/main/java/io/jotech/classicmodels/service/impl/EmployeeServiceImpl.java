package io.jotech.classicmodels.service.impl;


import java.util.List;

import io.jotech.classicmodels.entity.Employee;
import io.jotech.classicmodels.repository.EmployeeRepository;
import io.jotech.classicmodels.service.EmployeeService;
import jakarta.inject.Inject;

public class EmployeeServiceImpl implements EmployeeService {
    @Inject
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees(Integer start, Integer limit) {
        return employeeRepository.listAll(start,limit);
    }
}

package io.jotech.classicmodels.service;


import java.util.List;

import io.jotech.classicmodels.entity.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees(Integer start, Integer limit);

}

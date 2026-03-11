package com.basic.security.BasicAuthenticationDemo.service;

import com.basic.security.BasicAuthenticationDemo.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void removeEmployee(Long id);
    List<Employee> getAllEmployees();

    Employee findEmployeeById(Long id);
}

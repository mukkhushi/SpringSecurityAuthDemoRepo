package com.basic.security.BasicAuthenticationDemo.service;

import com.basic.security.BasicAuthenticationDemo.model.Employee;
import com.basic.security.BasicAuthenticationDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
         return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee emp = new Employee();
        emp.setName(employee.getName());
        emp.setDesignation(employee.getDesignation());
        emp.setId(employee.getId());
        return employeeRepository.save(emp);
    }

    @Override
    public void removeEmployee(Long id) {
        employeeRepository.delete(employeeRepository.findById(id).get());
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }
}

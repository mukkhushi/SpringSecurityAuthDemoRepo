package com.basic.security.BasicAuthenticationDemo.controller;

import com.basic.security.BasicAuthenticationDemo.model.Employee;
import com.basic.security.BasicAuthenticationDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //save employee
    @PostMapping("/create")
    String createEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.createEmployee(employee);
        if(employee1 != null){
            return "Employee is saved";
        }else {
            return "Employee is not saved";
        }
    }

    @PostMapping("/update")
    String updateEmployee(@RequestBody Employee employee){
        Employee employee1 = employeeService.updateEmployee(employee);
        if(employee1 != null){
            return "Employee is updated";
        }else {
            return "Employee is not updated";
        }
    }

    @DeleteMapping("/delete/{id}")
    void removeEmployee(@PathVariable Long id){
        employeeService.removeEmployee(id);
    }

    @GetMapping("/findAll")
    List<Employee> getAllEmployee(){
        return employeeService.getAllEmployees();
    }
}

package com.employeeManagement.employee.services;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;

import java.util.ArrayList;


public interface EmployeeService {
  Employee createEmployee(EmployeeRequest employeeRequest);

  ArrayList<Employee> getAllEmployees();

  EmployeeResponse deleteEmployee(Long id);

  Employee getEmployee(Long id);

  Employee updateEmployee(Long id);
}

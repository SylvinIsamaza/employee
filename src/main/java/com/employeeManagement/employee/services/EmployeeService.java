package com.employeeManagement.employee.services;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;
import com.employeeManagement.employee.model.UpdateEmployeeRequest;
import com.employeeManagement.employee.repository.EmployeeRepository;

import java.util.ArrayList;


public interface EmployeeService {
  Employee createEmployee(EmployeeRequest employeeRequest);

  ArrayList<Employee> getAllEmployees(int pageNumber, int pageSize);

  EmployeeResponse deleteEmployee(Long id);

  Employee getEmployee(Long id);

  Employee updateEmployee(Long id, UpdateEmployeeRequest updateEmployeeRequest);

}

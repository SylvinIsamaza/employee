package com.employeeManagement.employee.services;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;


public interface EmployeeService {
  Employee createEmployee(EmployeeRequest employeeRequest);
}

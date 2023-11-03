package com.employeeManagement.employee.impl;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.repository.EmployeeRepository;
import com.employeeManagement.employee.services.EmployeeService;
import com.employeeManagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var employee = Employee.builder()
                .name(employeeRequest.getName())
                .department(employeeRequest.getDepartment())
                .jobTitle(employeeRequest.getJobTitle())
                .salary(employeeRequest.getSalary())
                .endDate(employeeRequest.getEndDate())
                .phoneNumber(employeeRequest.getPhoneNumber())
                .createdBy(user)
                .build();
        return repository.save(employee);
    }
}

package com.employeeManagement.employee.impl;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;
import com.employeeManagement.employee.repository.EmployeeRepository;
import com.employeeManagement.employee.services.EmployeeService;
import com.employeeManagement.exception.ApiRequestException;
import com.employeeManagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;

    @Override
    public Employee createEmployee(EmployeeRequest employeeRequest) {
        log.info(employeeRequest.getSalary());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var employee = Employee.builder().name(employeeRequest.getName()).department(employeeRequest.getDepartment()).jobTitle(employeeRequest.getJobTitle()).salary(employeeRequest.getSalary()).endDate(employeeRequest.getEndDate()).phoneNumber(employeeRequest.getPhoneNumber()).createdBy(user).build();

        return repository.save(employee);

    }

    @Override
    public ArrayList<Employee> getAllEmployees() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        return repository.findByCreatedByEmail(userEmail);
    }

    @Override
    public EmployeeResponse deleteEmployee(Long id) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        Employee employee = repository.findByCreatedByEmailAndId(userEmail, id);
        if (employee != null) {
            repository.deleteById(id);
            return EmployeeResponse.builder().email(userEmail).name(employee.getName()).message(" employee hae been deleted successfully").build();

        }

        throw new ApiRequestException(" employee created by that user doesn't exits or employee with that id not found", HttpStatus.NOT_FOUND);


    }

    @Override
    public Employee getEmployee(Long id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        Employee employee = repository.findByCreatedByEmailAndId(userEmail, id);
        if (employee != null) {
            return employee;
        }

        throw new ApiRequestException(" employee created by that user doesn't exits or employee with that id not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public Employee updateEmployee(Long id) {
        return null;
    }
}

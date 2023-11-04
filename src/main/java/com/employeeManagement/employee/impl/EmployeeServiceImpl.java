package com.employeeManagement.employee.impl;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;
import com.employeeManagement.employee.model.UpdateEmployeeRequest;
import com.employeeManagement.employee.repository.EmployeeRepository;
import com.employeeManagement.employee.services.EmployeeService;
import com.employeeManagement.exception.ApiRequestException;
import com.employeeManagement.user.entities.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;


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
    public ArrayList<Employee> getAllEmployees(int page , int size) {
        Sort sort = Sort.by(Sort.Direction.ASC,"name");
        log.info(sort.toString());
        Pageable pageable = PageRequest.of(page,size,sort);
        SecurityContext securityContext = SecurityContextHolder.getContext();

        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        return repository.findByCreatedByEmail(userEmail,pageable);
    }

    @Override
    public EmployeeResponse deleteEmployee(UUID id) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        Employee employee = repository.findByCreatedByEmailAndId(userEmail, id);
        if (employee != null) {
            repository.deleteById(id);
            return EmployeeResponse.builder().email(userEmail).name(employee.getName()).message(" employee has been deleted successfully").build();

        }
        repository.findById(id).orElseThrow(() -> new ApiRequestException(" employee with that id not found", HttpStatus.NOT_FOUND));

        throw new ApiRequestException(" employee created by that user doesn't exits ", HttpStatus.NOT_FOUND);


    }

    @Override
    public Employee getEmployee(UUID id) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        Employee employee = repository.findByCreatedByEmailAndId(userEmail, id);
        if (employee != null) {
            return employee;
        }

        repository.findById(id).orElseThrow(() -> new ApiRequestException(" employee with that id not found", HttpStatus.NOT_FOUND));

        throw new ApiRequestException(" employee created by that user doesn't exits ", HttpStatus.NOT_FOUND);

    }

    @Override
    public Employee updateEmployee(UUID id, UpdateEmployeeRequest updateEmployeeRequest) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        var user = (User) securityContext.getAuthentication().getPrincipal();
        var userEmail = user.getUsername();
        Employee employee = repository.findByCreatedByEmailAndId(userEmail, id);
        if (employee != null) {
            if (updateEmployeeRequest.getName() != null) {
                employee.setName(updateEmployeeRequest.getName());
            }
            if (updateEmployeeRequest.getSalary() != null) {
                employee.setSalary(updateEmployeeRequest.getSalary());
            }
            if (updateEmployeeRequest.getDepartment() != null) {
                employee.setDepartment(updateEmployeeRequest.getDepartment());
            }
            if (updateEmployeeRequest.getJobTitle() != null) {
                employee.setJobTitle(updateEmployeeRequest.getJobTitle());
            }
            if (updateEmployeeRequest.getEndDate() != null) {
                employee.setEndDate(updateEmployeeRequest.getEndDate());
            }
            return repository.save(employee);
        }

        repository.findById(id).orElseThrow(() -> new ApiRequestException(" employee with that id not found", HttpStatus.NOT_FOUND));
        throw new ApiRequestException(" employee created by that user doesn't exits ", HttpStatus.NOT_FOUND);


    }
}

package com.employeeManagement.employee.controller;

import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping("/createEmployee")
    ResponseEntity<?> createEmployee(@RequestBody EmployeeRequest employeeRequest){
        log.info("reaching out");
   return  ResponseEntity.ok().body(service.createEmployee(employeeRequest));
//   return ResponseEntity.ok().body("eee");
    }

}

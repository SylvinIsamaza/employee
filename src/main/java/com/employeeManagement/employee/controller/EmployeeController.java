package com.employeeManagement.employee.controller;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;
import com.employeeManagement.employee.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService service;

    @PostMapping("/createEmployee")
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest){
   return  ResponseEntity.ok().body(service.createEmployee(employeeRequest));
    }

    @GetMapping("/getEmployees")
    ResponseEntity<ArrayList<Employee>> getEmployees(){
        return  ResponseEntity.ok().body(service.getAllEmployees());
    }
    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable Long id ){
        return  ResponseEntity.ok().body(service.deleteEmployee(id));
    }

    @GetMapping("/getEmployee/{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable Long id){
return  ResponseEntity.ok().body(service.getEmployee(id));
    }
    @PatchMapping("/updateEmploye/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable Long id){
        return  ResponseEntity.ok().body(service.updateEmployee(id));
    }

}

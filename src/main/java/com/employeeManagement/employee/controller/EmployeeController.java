package com.employeeManagement.employee.controller;

import com.employeeManagement.employee.Entity.Employee;
import com.employeeManagement.employee.model.EmployeeRequest;
import com.employeeManagement.employee.model.EmployeeResponse;
import com.employeeManagement.employee.model.UpdateEmployeeRequest;
import com.employeeManagement.employee.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
@Slf4j
@Tag( name ="employee management")
public class EmployeeController {
    private final EmployeeService service;


    @Operation(
            summary = " User new employee",
            description = "This protected operation allows  users to create employee by providing a valid access token in the request header and new credentials of your new employee ",
            responses = {
                    @ApiResponse(
                            description = "Profile updated successfully.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Invalid or unauthorized access. Please check your token.",
                            responseCode = "403 or 401"
                    )
            },
            method = "create employee"

    )
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/createEmployee")
    ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest){
   return  ResponseEntity.ok().body(service.createEmployee(employeeRequest));
    }





    @Operation(
            summary = "Unlock the Power of JWT: Retrieve employees  Details ",
            description = "Welcome to our Secured Gateway for Employee Information Retrieval. This powerful gateway ensures the utmost security, allowing you to access employee data with your token included in the request header. With this API, you can effortlessly retrieve detailed information about all the users you have created, sorted to your preferences. You have full control over the page size and page numbers, making it convenient to navigate and explore the data you need."
,
            responses = {
                    @ApiResponse(
                            description = "Success! employees details are at your fingertips.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Uh-oh! Your token appears to be invalid or unauthorized.",
                            responseCode = "403 or 401"
                    ),

            }, method = "get employees details"


    )
    @SecurityRequirement(name = "bearerAuth")

    @GetMapping("/getEmployees")
    ResponseEntity<ArrayList<Employee>> getEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size ){
        return  ResponseEntity.ok().body(service.getAllEmployees(page,size));
    }


    @Operation(
            summary = "Terminate employee Gracefully",
            description = "Safeguarded operation for deleting employee details via a token in the header   and employee id .",
            responses = {
                    @ApiResponse(
                            description = "Your account has been gracefully terminated. A confirmation email has been sent.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Oops! Your token is invalid or unauthorized. Account deletion failed.",
                            responseCode = "403 or 401"
                    ),
                    @ApiResponse(
                            description = "employee id  not found. Deletion request aborted.",
                            responseCode = "404"
                    )
            },
            method = "deleteEmployee"
    )
    @SecurityRequirement(name = "bearerAuth")

    @DeleteMapping("/deleteEmployee/{id}")
    ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable UUID id ){
        return  ResponseEntity.ok().body(service.deleteEmployee(id));
    }
    @Operation(
            summary = "Unlock the Power of JWT: Retrieve employee  Details ",
            description = "Discover the secured gateway to retrieve employee information by including your token in the request header. and id of your employee to access her or his information",
            responses = {
                    @ApiResponse(
                            description = "Success! employee details are at your fingertips.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Uh-oh! Your token appears to be invalid or unauthorized.",
                            responseCode = "403 or 401"
                    ),
                    @ApiResponse(
                            description = "Uh-oh!  your employee doesn't exists",
                            responseCode = "404 "
                    )
            }, method = "get employee details"


    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/getEmployee/{id}")
    ResponseEntity<Employee> getEmployee(@PathVariable UUID id){
return  ResponseEntity.ok().body(service.getEmployee(id));
    }


    @Operation(
            summary = "Update employee Profile",
            description = "This protected operation allows  users to update employees profile  they have created by providing a valid access token in the request header , new credentials and  forget to provide  id of specific employee you want to  update",
            responses = {
                    @ApiResponse(
                            description = "Profile updated successfully.",
                            responseCode = "200",
                            useReturnTypeSchema = true
                    ),
                    @ApiResponse(
                            description = "Invalid or unauthorized access. Please check your token.",
                            responseCode = "403 or 401"
                    ),
                    @ApiResponse(
                            description = "employee  not found. The specified employee with that id does not exist.",
                            responseCode = "404"
                    )
            },
            method = "update employee"

    )
    @SecurityRequirement(name = "bearerAuth")
    @PatchMapping("/updateEmployee/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable UUID id , @RequestBody UpdateEmployeeRequest updateEmployeeRequest){
        return  ResponseEntity.ok().body(service.updateEmployee(id,updateEmployeeRequest));
    }

}

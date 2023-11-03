package com.employeeManagement.employee.model;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class EmployeeRequest {
    private String Name;
    private String department;
    private String jobTitle;
    private String salary;
    private String startDate;
    private String endDate;
    private String phoneNumber;
    private Email email;

}

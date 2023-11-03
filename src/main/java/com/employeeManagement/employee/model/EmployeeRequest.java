package com.employeeManagement.employee.model;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeRequest {
    private String Name;
    private String department;
    private String jobTitle;
    private String salary;
    private Date endDate;
    private String phoneNumber;
    private String email;

}

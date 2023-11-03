package com.employeeManagement.employee.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class EmployeeRequest {
    private String Name;
    private String department;
    private String jobTitle;
    private String salary;
    private Date endDate;
    private String phoneNumber;

}

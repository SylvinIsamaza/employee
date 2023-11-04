package com.employeeManagement.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEmployeeRequest {
    private  String name;
    private  String department;
    private  String salary;
    private  String jobTitle;
    private  String phoneNumber;
    private Date endDate;
}

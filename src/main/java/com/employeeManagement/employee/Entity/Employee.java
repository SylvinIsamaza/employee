package com.employeeManagement.employee.Entity;

import com.employeeManagement.user.entities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")

public class Employee  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String department;
    private String jobTitle;
    private String salary;
    @Builder.Default
    private Date startDate = new Date();
    private Date endDate;
    private String phoneNumber;
    @ManyToOne
    @JoinColumn(name = "created_by" ,referencedColumnName = "email")
    private User createdBy;
}

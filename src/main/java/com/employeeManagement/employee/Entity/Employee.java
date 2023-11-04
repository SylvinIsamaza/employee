package com.employeeManagement.employee.Entity;

import com.employeeManagement.user.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String department;
    private String jobTitle;

    //TODO: not working to  add  default value for column
    @Column(name = "salary", columnDefinition = "VARCHAR(255) DEFAULT '2,000 $'")
    private String salary;

    @Builder.Default
    private Date startDate = new Date();
    private Date endDate;
    private String phoneNumber;

    //    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne
    @JoinColumn(name = "created_by", referencedColumnName = "email")
    @JsonIgnoreProperties({"id", "fullName", "email", "phoneNumber", "password", "role", "enabled", "authorities", "accountNonExpired", "accountNonLocked", "credentialsNonExpired"})
    private User createdBy;

    @PrePersist
    public void setDefault() {
        salary = " 2,000 $";
        endDate = new Date(2030);

    }
}
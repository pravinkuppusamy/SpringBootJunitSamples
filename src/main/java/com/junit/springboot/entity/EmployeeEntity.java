package com.junit.springboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;

@Table(name = "employee")
@Entity
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

    @Column
    private String name;

    @Id
    private String id;

    @Column
    private String dob;

}

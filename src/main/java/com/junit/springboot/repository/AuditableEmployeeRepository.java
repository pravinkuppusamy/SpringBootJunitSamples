package com.junit.springboot.repository;

import com.junit.springboot.entity.AuditableEmployeeEntity;
import com.junit.springboot.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditableEmployeeRepository extends JpaRepository<AuditableEmployeeEntity, String> {
}

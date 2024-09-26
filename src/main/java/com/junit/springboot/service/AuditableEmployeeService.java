package com.junit.springboot.service;

import com.junit.springboot.dto.AuditableEmployeeRequestDto;
import com.junit.springboot.dto.AuditableEmployeeResponseDto;
import com.junit.springboot.entity.AuditableEmployeeEntity;
import com.junit.springboot.repository.AuditableEmployeeRepository;
import com.junit.springboot.utils.CommonConstant;
import com.junit.springboot.utils.HttpUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditableEmployeeService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    AuditableEmployeeRepository auditableEmployeeRepository;

    public AuditableEmployeeResponseDto getEmployee(String id) {
        AuditableEmployeeEntity auditableEmployeeEntity = auditableEmployeeRepository.getReferenceById(id);
        return AuditableEmployeeResponseDto.builder().dob(auditableEmployeeEntity.getDob()).id(auditableEmployeeEntity.getId())
                .name(auditableEmployeeEntity.getName()).createdBy(auditableEmployeeEntity.getCreatedBy()).createdAt(auditableEmployeeEntity.getCreatedAt()).build();

    }

    public void createEmployee(AuditableEmployeeRequestDto employeeDto) {
        HttpUtils.getHeaderValue(CommonConstant.X_REQUESTER_NAME);
        AuditableEmployeeEntity employeeEntity =  new AuditableEmployeeEntity(employeeDto.name(),employeeDto.id(),employeeDto.dob());
        auditableEmployeeRepository.save(employeeEntity);
    }
}

package com.junit.springboot.service;

import com.junit.springboot.dto.EmployeeDto;
import com.junit.springboot.entity.EmployeeEntity;
import com.junit.springboot.repository.EmployeeRepository;
import com.junit.springboot.utils.CommonConstant;
import com.junit.springboot.utils.HttpUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto getEmployee(String id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        return new EmployeeDto(employeeEntity.getName(), employeeEntity.getId(), employeeEntity.getDob());
    }

    public void createEmployee(EmployeeDto employeeDto) {
        HttpUtils.getHeaderValue(CommonConstant.X_REQUESTER_NAME);
        EmployeeEntity employeeEntity =  new EmployeeEntity(employeeDto.name(),employeeDto.id(),employeeDto.dob());
        employeeRepository.save(employeeEntity);
    }
}

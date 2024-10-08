package com.junit.springboot;

import com.junit.springboot.controller.EmployeeController;
import com.junit.springboot.dto.EmployeeDto;
import com.junit.springboot.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest(classes = com.example.springboot.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    EmployeeController employeeController;


    @Test
    public void testEmployeeController()
    {
       EmployeeDto employeeDtoResponseEntity = employeeController.get("e1234");
        Assertions.assertNotNull(employeeDtoResponseEntity);
    }
}

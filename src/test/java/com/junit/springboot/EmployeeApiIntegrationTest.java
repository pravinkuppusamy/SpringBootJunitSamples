package com.junit.springboot;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.junit.springboot.dto.EmployeeDto;
import com.junit.springboot.repository.EmployeeRepository;
import com.junit.springboot.service.EmployeeService;
import com.junit.springboot.utils.CommonConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.springframework.http.MediaType.APPLICATION_JSON;


@SpringBootTest(classes = com.example.springboot.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmployeeApiIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }


    @Test
    void whenAllThenputParamsAreInvalidThenReturnBadRequest() throws Exception {

        String buName = "TestBUName";
        String buId = "BU1";
        String tenantId = "TestTenantId";
        String testUser1 = "TestUser1";
        String testUser2 = "TestUser2";


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        EmployeeDto employeeDto = new EmployeeDto("","","");

        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON).content(gson.toJson(employeeDto))
                        .header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)
                        .header(CommonConstant.X_TENANT_ID, tenantId)
                )
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals("{\"errors\":[\"dob must not be empty\",\"id must not be empty\",\"name must not be empty\"]}", response.getContentAsString());


    }

    @Test
    void whenIdIsMissedReturnBadRequestWithMissingIdInResponse() throws Exception {

        String buName = "TestBUName";
        String buId = "BU1";
        String tenantId = "TestTenantId";
        String testUser1 = "TestUser1";
        String testUser2 = "TestUser2";


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        EmployeeDto employeeDto = new EmployeeDto("","test","test");

        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON).content(gson.toJson(employeeDto))
                        .header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)
                        .header(CommonConstant.X_TENANT_ID, tenantId)
                )
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals("{\"id must not be empty\"}", response.getContentAsString());


    }

    @Test
    void whenTheInputParamsAreValidReturnSuccessResponse() throws Exception {

        String buName = "TestBUName";
        String buId = "BU1";
        String tenantId = "TestTenantId";
        String testUser1 = "TestUser1";
        String testUser2 = "TestUser2";


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        EmployeeDto employeeDto = EmployeeDto.builder().name("Pravin").id("e123").dob("12-feb-2020").build();

        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON).content(gson.toJson(employeeDto))
                        .header("Origin", "http://dev-erp.dpworld.com").
                        header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)
                        .header(CommonConstant.X_TENANT_ID, tenantId)
                )
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), response.getStatus());

        MockHttpServletResponse getResponse = this.mvc.perform(MockMvcRequestBuilders.get("/").contentType(APPLICATION_JSON).content(gson.toJson(employeeDto))
                        .header("Origin", "http://dev-erp.dpworld.com")
                .header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)
                        .header(CommonConstant.X_TENANT_ID, tenantId)
                        .param("id", "e123")
                )
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.OK.value(), getResponse.getStatus());
        System.out.println(getResponse.getContentAsString());


    }


    @Test
    void whenUnavailableEmployeeIdIsQueriedRespondPageNotFound() throws Exception {

        String buName = "TestBUName";
        String buId = "BU1";
        String tenantId = "TestTenantId";
        String testUser1 = "TestUser1";
        String testUser2 = "TestUser2";



        MockHttpServletResponse getResponse = this.mvc.perform(MockMvcRequestBuilders.get("/")
                        .header("Origin", "http://dev-erp.dpworld.com")
                .header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)
                        .header(CommonConstant.X_TENANT_ID, tenantId)
                        .param("id", "e1234"))
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), getResponse.getStatus());
        Assertions.assertEquals("{\"errors\":[\"Unable to find com.junit.springboot.entity.EmployeeEntity with id e1234\"]}", getResponse.getContentAsString());

    }


}
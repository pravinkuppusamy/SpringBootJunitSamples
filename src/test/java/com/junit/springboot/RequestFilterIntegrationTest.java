package com.junit.springboot;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.junit.springboot.dto.EmployeeDto;
import com.junit.springboot.utils.CommonConstant;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
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
@Import(ServletRequestFilter.class)
class RequestFilterIntegrationTest {


    @Autowired
    ServletRequestFilter servletRequestFilter;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).addFilter(servletRequestFilter)
                .build();
    }


    @Test
    void whenInvalidInputParamsProvidedReturnBadRequest() throws Exception {

        String buId = "BU1";
        String tenantId = "TestTenantId";
        String testUser1 = "TestUser1";


        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        EmployeeDto employeeDto = new EmployeeDto("","","");

        MockHttpServletResponse response = this.mvc.perform(MockMvcRequestBuilders.post("/").contentType(APPLICATION_JSON).content(gson.toJson(employeeDto))

                                .header(CommonConstant.X_BU_ID, buId)
                        .header(CommonConstant.X_REQUESTER_NAME, testUser1)

                       // .header(CommonConstant.X_TENANT_ID, tenantId)
                )
                .andReturn()
                .getResponse();
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        Assertions.assertEquals("X-TenantID is a mandatory header", response.getContentAsString());


    }
}
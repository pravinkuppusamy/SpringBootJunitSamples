package com.junit.springboot;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = com.example.springboot.Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class WebConfigTest {


    private MockMvc mvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    void testGetCors() throws Exception {
        this.mvc.perform(get("/")
                        .header("Origin", "http://dev-erp.dpworld.com")).
                andDo(print())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://dev-erp.dpworld.com"));
    }

    @Test
    void testGetInvalidCors() {
        try {
            this.mvc.perform(get("/")
                            .header("Origin", "http://x-erp.dpworld_x.com/test").param("id", "e1234")).
                    andDo(print())
                    .andExpect(status().is(403));
        } catch (Exception e) {
e.printStackTrace();        }
    }


}
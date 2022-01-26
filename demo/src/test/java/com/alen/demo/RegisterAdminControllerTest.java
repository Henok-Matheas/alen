package com.alen.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(registerAdminController.class)
public class RegisterAdminControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAdminRegistrationPage() throws Exception {

        mockMvc.perform(get("/registerAdmin"))
            .andExpect(status().isOk())
            .andExpect(view().name("registerAdmin"));
    } 
}

package com.alen.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AdminController.class)
public class AdminControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAdminPage() throws Exception {

        mockMvc.perform(get("/admin"))
            .andExpect(status().isOk())
            .andExpect(view().name("admin"));

        mockMvc.perform(get("/listOfPharmacyAdmin"))
            .andExpect(status().isOk())
            .andExpect(view().name("listOfPharmacy"));

        mockMvc.perform(get("/listOfUserAdmin"))
            .andExpect(status().isOk())
            .andExpect(view().name("listOfUser"));

        mockMvc.perform(get("/updatePharmacyAdmin/{id}"))
            .andExpect(status().isOk())
            .andExpect(view().name("updatePharmacyAdmin"));

    }
}

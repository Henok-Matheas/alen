package com.alen.demo;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ViewUserController.class)
public class ViewUserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testViewUserPage() throws Exception {

        mockMvc.perform(get("/user"))
            .andExpect(status().isOk())
            .andExpect(view().name("user"))
            .andExpect(content().string(
                containsString("Your profile")));

}
}

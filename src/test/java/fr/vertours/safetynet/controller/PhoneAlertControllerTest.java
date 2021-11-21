package fr.vertours.safetynet.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PhoneAlertControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getListPhoneNumberByFireStation() throws Exception {
        mockMvc.perform(get("/phoneAlert?firestation=1"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0]", is("841-874-8547")));
    }
}
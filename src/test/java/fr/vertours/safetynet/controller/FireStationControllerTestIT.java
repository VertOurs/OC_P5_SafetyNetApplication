package fr.vertours.safetynet.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;



import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTestIT {

    @Autowired
    public MockMvc mockMvc;


    String updateFireStationDTO;
    String createFireStationDTO;

    @BeforeEach
    void setUp() {
        createFireStationDTO = "{\"" +
                "address\":[\"100, RUE DES 3 SAPINS\"]," +
                " \"station\":\"10\" }";

        updateFireStationDTO = "{\"" +
                "address\":[\"947 E. Rose Dr\"]," +
                " \"station\":\"2\" }";
    }

    @Test
    void getListFireStation() throws Exception {
        mockMvc.perform(get("/firestation/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].station", is(3)));
    }

    @Test
    void getStation() throws Exception {
        mockMvc.perform(get("/firestation/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("station", is(1)));
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/firestation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createFireStationDTO))
                        .andExpect(status().isOk());
    }

    @Test
    void updateNbStationForOneAddress() throws Exception {
//        mockMvc.perform(put("/firestation/2")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(updateFireStationDTO))
//                .andExpect(status().isOk());
        // créé une adresse.
    }

    @Test
    void deleteOneStation() throws Exception {
        mockMvc.perform(delete("/firestation/1"))
                .andExpect(status().isOk());
    }
}
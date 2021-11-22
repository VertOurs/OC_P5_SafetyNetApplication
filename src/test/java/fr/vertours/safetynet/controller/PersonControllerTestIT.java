package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.service.MedicalRecordService;
import fr.vertours.safetynet.service.PersonService;
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
class PersonControllerTestIT {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    MedicalRecordService medicalRecordService;



    @BeforeEach
    void setUp() {


    }

    @Test
    void getListOfPersons() throws Exception {
        mockMvc.perform(get("/person/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    void getOnePerson() throws Exception {
        mockMvc.perform(get("/person/John/Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is("John")));
    }

    @Test
    void registerNewPerson() throws Exception {
        String  createPersonDTO = "{\n"
                + "\"firstName\": \"Aymeric\",\n"
                + "\"lastName\": \"Perrin\",\n"
                + "\"address\": \"1551, tue Louis Blériot\",\n"
                + "\"city\": \"Clamart\",\n"
                + "\"zip\": \"94100\",\n"
                + "\"phone\": \"06 74 89 65 14\",\n"
                + "\"email\": \"AymericPer@gmail.com\"\n"
                + "}";
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createPersonDTO))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception {
        String updatePersonDTO = "{\n"
                + "\"firstName\": \"John\",\n"
                + "\"lastName\": \"Boyd\",\n"
                + "\"address\": \"1509 Culver St\",\n"
                + "\"city\": \"Culver\",\n"
                + "\"zip\": \"97451\",\n"
                + "\"phone\": \"NO CELLPHONE\",\n"
                + "\"email\": \"BOBYBOB.JOHNNY@GMAIL.COM\"\n"
                + "}";
        mockMvc.perform(put("/person/John/Boyd")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePersonDTO))
                .andExpect(status().isOk());
    }

    @Test
    void deletePerson() throws Exception {
        medicalRecordService.deleteOneMedicalRecord("John", "Boyd");
        mockMvc.perform(delete("/person/John/Boyd"))
                .andExpect(status().isOk());

    }
}
package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
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
class MedicalRecordControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PersonService personService;

    PersonDTO dto = new PersonDTO( "Aymeric",
            "Perrin",
            "5, rue des peupliers",
            "Saint-Lambert",
            "45890",
            "04 85 74 32 45",
            "activeService@gmail.com");
    String createMedicalRecordDTO;
    String updateMedicalRecordDTO;


    @BeforeEach
    void setUp() {
        createMedicalRecordDTO = "{\n"
                + "\"firstName\": \"Aymeric\",\n"
                + "\"lastName\": \"Perrin\",\n"
                + "\"birthdate\": \"01/01/2000\",\n"
                + "\"allergies\": [],\n"
                + "\"medications\": [\"hydrapermazol:100mg\"]\n"
                + "}";
        updateMedicalRecordDTO = "{\n"
                + "\"firstName\": \"John\",\n"
                + "\"lastName\": \"Boyd\",\n"
                + "\"birthdate\": \"01/01/2000\",\n"
                + "\"allergies\": [],\n"
                + "\"medications\": [\"hydrapermazol:100mg\"]\n"
                + "}";
    }

    @Test
    void getListOfMedicalRecord() throws Exception {
        mockMvc.perform(get("/medicalRecord/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    void getOneMedicalRecord() throws Exception {
        mockMvc.perform(get("/medicalRecord/John/Boyd"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("firstName", is("John")));
    }

    @Test
    void registerNewMedicalPerson() throws Exception {
        personService.addPerson(dto);
        mockMvc.perform(post("/medicalRecord")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createMedicalRecordDTO))
                .andExpect(status().isOk());
    }

    @Test
    void updateMedicalRecord() throws Exception {
        mockMvc.perform(put("/medicalRecord/John/Boyd")
                .contentType(MediaType.APPLICATION_JSON)
                .content(updateMedicalRecordDTO))
                .andExpect(status().isOk());
    }

    @Test
    void deleteOneMedicalRecord() throws Exception {
        mockMvc.perform(delete("/medicalRecord/John/Boyd"))
                .andExpect(status().isOk());
    }
}
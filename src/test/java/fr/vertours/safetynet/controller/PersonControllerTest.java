package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    public MockMvc mockMvc;

    String personDTO;
    @BeforeEach
    void setUp() {
        personDTO = "{\n"
                + "\"firstName\": \"Aymeric\",\n"
                + "\"lastName\": \"Perrin\",\n"
                + "\"address\": \"1551, tue Louis Blériot\",\n"
                + "\"city\": \"Clamart\",\n"
                + "\"zip\": \"94100\",\n"
                + "\"phone\": \"06 74 89 65 14\",\n"
                + "\"email\": \"AymericPer@gmail.com\"\n"
                + "}";
    }

    @Test
    void getListOfPersons() throws Exception {
        mockMvc.perform(get("/person/all"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    void getOnePerson() throws Exception {
        mockMvc.perform(get("/person/John/Boyd"))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("firstName", is("John")));
    }

    @Test
    void registerNewPerson() throws Exception {
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(personDTO))
                .andExpect(status().isAccepted());
    }

    @Test
    void updatePerson() {
    }

    @Test
    void deletePerson() {
    }
}
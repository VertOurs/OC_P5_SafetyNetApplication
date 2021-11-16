package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.dto.PersonInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    PersonInfoService personInfoService;

    private String firstName;
    private  String lastName;

    @BeforeEach
    private void setUpEachTest() {
        firstName = "Matthieu";
        lastName = "Martin";
        Person person = new Person(firstName, lastName,
                new Address("85, route du Port"), "MARNEY",
                "71360", "03 85 96 21 86", "matthieu.martin@protonmail.com");

    }

    @Test
    void getNameAddressAgeMailMedicationsAndAllergies() throws Exception {
        when(personInfoService.find(firstName, lastName)).thenReturn(eq(any(Person.class)));
        //mockMvc.perform(get("/personInfo?firstName=Matthieu&lastName=Martin")).andExpect(status().isOk());
    }
}
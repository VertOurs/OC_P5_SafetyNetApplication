package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@RunWith(SpringRunner.class)(SpringJunit5ClassRunner)
//WebAppCOnfiguration
//COntextConfiguration (classes = {RestCOnfigTest.class})
@AutoConfigureMockMvc
class PersonControllerTest {

    // test unitaire  sur controller pertinent? ou juste It ? les deux?
    @Autowired
    MockMvc mockMvc;

    @MockBean//(spring)  ** @Mock (mockito) ** @InjectMock **@Autowired ? juste le sens?
    // Pourquoi mocker dans le cadre d'un tests d'intégration (@MockBean)
    PersonService personService;

    Person person;
    List<Person> personList;


    @BeforeEach
    void setUp() {
        person = new Person();
        person.setLastName("Boyd");
        person.setFirstName("John");
        person.setAddress(new Address("1509 Culver St"));
        person.setCity("Culver");
        person.setPhone("841-874-6512");
        person.setEmail("jaboyd@email.com");
        person.setZip("97451");

        personList = new ArrayList<>();
        personList.add(person);


    }

    //@Test
    /*void getListOfPersons() throws Exception {
        when(personService.getAllPersons().thenReturn(person));
        mockMvc.perform(get("/person")).andExpect(status().isOk());  //this devant mockMvc?
    }*/
    @Disabled
    @Test
    void getOnePerson() {

    }
    @Disabled
    @Test
    void registerNewPerson() {
    }
    @Disabled
    @Test
    void updatePerson() {
    }
    @Disabled
    @Test
    void deletePerson() {
    }
}
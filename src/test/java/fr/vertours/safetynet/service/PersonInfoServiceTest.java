package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonInfoServiceTest {

    PersonService personService = mock(PersonService.class);
    PersonInfoService classUnderTest = new PersonInfoService(personService);

    Person person = new Person();
    Address address = new Address("5, rue des peupliers");

    @BeforeEach
    void setUp() {
        person.setFirstName("Roger");
        person.setLastName("Borgne");
        person.setAddress(address);
        person.setCity("Saint-Lambert");
        person.setZip("45890");
        person.setPhone("04 85 74 32 45");
        person.setEmail("activeService@gmail.com");
    }

    @Test
    void find() {
        when(personService.find("Roger","Borgne")).thenReturn(person);
        Person personTested = classUnderTest.find("Roger","Borgne");

        assertEquals(person, personTested);
        assertEquals("45890", personTested.getZip());
    }
}
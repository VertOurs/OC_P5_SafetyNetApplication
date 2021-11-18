package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmailServiceTest {

    PersonService personService = mock(PersonService.class);

    EmailService classUnderTest = new EmailService(personService);

    String city = "LibreVille";
    Person roger = new Person("Roger","Borgne",new Address("dans le monde"),
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");
    List<Person> people = new ArrayList<>();

    @BeforeEach
    void setUp() {
        people.add(roger);
    }

    @Test
    void findByCity() {
        when(personService.findByCity(city)).thenReturn(people);
        List<Person> listTested = classUnderTest.findByCity(city);

        assertEquals(people,listTested);
        assertEquals(people.get(0).getPhone(), listTested.get(0).getPhone());
    }
}
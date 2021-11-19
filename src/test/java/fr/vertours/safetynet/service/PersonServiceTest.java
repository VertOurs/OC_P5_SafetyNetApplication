package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {




    PersonRepository personRepository = mock(PersonRepository.class);
    AddressService addressService = mock(AddressService.class);



    PersonService classUnderTest = new PersonService(personRepository,
            addressService
            );

    PersonDTO personDTO1 = new PersonDTO("Roger",
            "Borgne",
            "5, rue des peupliers",
            "Saint-Lambert",
            "45890",
            "04 85 74 32 45",
            "activeService@gmail.com"
            );
    Address address = new Address("5, rue des peupliers");
    Person person = new Person("Roger","Borgne",address,
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");
    @BeforeEach
    void setUp() {


    }

    @Test
    void addPerson() {
        when(addressService.findOrCreate(personDTO1.getAddress())).thenReturn(address);
        when(personRepository.findOneByFirstNameAndLastName(
                personDTO1.getFirstName(),
                personDTO1.getLastName()))
                .thenReturn(null);
        when(personRepository.save(person)).thenReturn(person);

        classUnderTest.addPerson(personDTO1);

        verify(personRepository, times(1)).save(person);
//        Person person = new Person();
//        PersonDTO personDTO = new PersonDTO();
//
//        personService.addPerson(personDTO);
//        verify(personRepository, times(1)).save(person);

    }

    @Test
    void saveAll() {
    }

    @Test
    void getAllPersons() {
    }

    @Test
    void deletePerson() {
    }

    @Test
    void find() {
    }

    @Test
    void findByCity() {
    }

    @Test
    void findByAddress() {
    }

    @Test
    void findByAddressIn() {
    }

    @Test
    void findByStation() {
    }

    @Test
    void updatePerson() {
    }

    @Test
    void personFromFireStation() {
    }

    @Test
    void getFireStationInfoDTOFromList() {
    }

    @Test
    void findListOfPersonByAddress() {
    }
}
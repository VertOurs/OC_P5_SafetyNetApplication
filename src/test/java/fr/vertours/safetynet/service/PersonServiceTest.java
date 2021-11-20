package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PersonServiceTest {





    PersonRepository personRepository = mock(PersonRepository.class);
    AddressService addressService = mock(AddressService.class);





    PersonService classUnderTest = new PersonService(personRepository,
            addressService
            );

    PersonService personServiceSpy = Mockito.spy(classUnderTest);

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
    List<Person> personList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        personList.add(person);

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


    }

    @Test
    void saveAll() {
        when(personRepository.saveAll(personList)).thenReturn(personList);
        classUnderTest.saveAll(personList);
        verify(personRepository, times(1)).saveAll(personList);
    }

    @Test
    void getAllPersons() {
        when(personRepository.findAll()).thenReturn(personList);
        classUnderTest.getAllPersons();
        verify(personRepository, times(2)).findAll();
    }

    @Test
    void deletePerson() {

        /* ESSAI DE SIMULATION DE FIND()*/
         when(personServiceSpy.find(person.getFirstName(),person.getLastName())).thenReturn(person);
        //Mockito.doReturn(person).when(personServiceSpy).find(person.getFirstName(),person.getLastName());
        //doReturn(person).when(personServiceSpy.find(person.getFirstName(),person.getLastName()));
        //Mockito.doReturn(person).when(personServiceSpy.find(person.getFirstName(),person.getLastName()));
        //Mockito.when(personServiceSpy).find(person.getFirstName(),person.getLastName())).thenReturn(person);

        /* MOCK DE PERSONREPOSITORY */
        doNothing().when(personRepository).delete(person);

        verify(personServiceSpy,times(1)).find(person.getFirstName(),person.getLastName());
    }

    @Test
    void find() {
        when(personRepository.findOneByFirstNameAndLastName("Roger", "Borgne")).thenReturn(person);
        classUnderTest.find(person.getFirstName(), person.getLastName());
        verify(personRepository, times(2)).findOneByFirstNameAndLastName("Roger","Borgne");
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
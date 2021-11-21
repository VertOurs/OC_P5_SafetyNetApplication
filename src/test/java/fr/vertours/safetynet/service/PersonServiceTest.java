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
    List<Address> addressList = new ArrayList<>();
    @BeforeEach
    void setUp() {
        personList.add(person);
        addressList.add(address);

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
        when(personRepository.findOneByFirstNameAndLastName("Roger", "Borgne"))
                .thenReturn(person);
        doNothing().when(personRepository).delete(person);

        classUnderTest.deletePerson("Roger", "Borgne");

        verify(personRepository,times(1)).delete(person);
    }

    @Test
    void find() {
        when(personRepository.findOneByFirstNameAndLastName("Roger", "Borgne")).thenReturn(person);
        classUnderTest.find(person.getFirstName(), person.getLastName());
        verify(personRepository, times(2)).findOneByFirstNameAndLastName("Roger","Borgne");
    }

    @Test
    void findByCity() {
        when(personRepository.findAllByCity(person.getCity())).thenReturn(personList);
        classUnderTest.findByCity("Saint-Lambert");
        verify(personRepository, times(1)).findAllByCity("Saint-Lambert");
    }

    @Test
    void findByAddress() {
        when(personRepository.findByAddress_AddressName(address.getAddressName()))
                .thenReturn(personList);
        classUnderTest.findByAddress("5, rue des peupliers");
        verify(personRepository, times(1))
                .findByAddress_AddressName("5, rue des peupliers");
    }

    @Test
    void findByAddressIn() {
        when(personRepository.findByAddressIn(addressList))
                .thenReturn(personList);
        classUnderTest.findByAddressIn(addressList);
        verify(personRepository, times(1))
                .findByAddressIn(addressList);
    }

    @Test
    void updatePerson() {
        when(personRepository.findOneByFirstNameAndLastName("Roger", "Borgne"))
                .thenReturn(person);
        when(personRepository.save(person)).thenReturn(person);

        classUnderTest.updatePerson(person.getFirstName(),
                person.getLastName(),
                personDTO1);

        verify(personRepository, times(1))
                .findOneByFirstNameAndLastName("Roger","Borgne");
        verify(personRepository, times(1)).save(person);
    }

    @Test
    void findListOfPersonByAddress() {
        when(personRepository.findByAddress_AddressName("5, rue des peupliers"))
                .thenReturn(personList);

        classUnderTest.findListOfPersonByAddress(address);

        verify(personRepository, times(1))
                .findByAddress_AddressName(address.getAddressName());
    }
}
package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.FireStationRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class PhoneAlertServiceTest {

    @MockBean
    FireStationService fireStationService;

    @MockBean
    PersonService personService;

    @Autowired
    PhoneAlertService phoneAlertService;


    PersonRepository repoMockPerson = mock(PersonRepository.class);
    FireStationRepository repoMockFireStation = mock(FireStationRepository.class);



    @Test
    void findByStation() {
        List<Address> addressList = new ArrayList<>();
        FireStation fireStation = new FireStation();
        List<Person> personList = new ArrayList<>();
        Address address1 = new Address("5, rue des peuplier");
        Address address2 = new Address("48, avenue du posafol");
        addressList.add(address1);
        addressList.add(address2);
        fireStation.setStation(5);
        fireStation.setId((long) 5);
        fireStation.setAddress(addressList);

        personList.add(new Person("Roger","Borgne",address1,
                "Saint-Lambert","45890","04 85 74 32 45",
                "activeService@gmail.com"));
        personList.add(new Person("Samuel","reghem",address2,
                "Pochard","12800","01 85 36 32 48",
                "activeService@gmail.com"));

        //when(fireStationService.findOneStation(anyInt())).thenReturn(repoMockFireStation);
        when(repoMockFireStation.findByStation(anyInt())).thenReturn(fireStation);
        when(repoMockPerson.findByAddressIn(anyCollection())).thenReturn(personList);

        //when(repoMockFireStation.personService.findByAddressIn(anyCollection())).thenReturn(personList);

        //List<Person> resultList = phoneAlertService.findByStation(5);



        verify(fireStationService, times(1)).findOneStation(anyInt());
        verify(personService, times(1)).findByAddressIn(anyCollection());

       // assertEquals(personList, resultList);


    }
}
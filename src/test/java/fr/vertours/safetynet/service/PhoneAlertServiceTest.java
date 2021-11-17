package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;



import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PhoneAlertServiceTest {

    PersonService personService = mock(PersonService.class);
    FireStationService fireStationService = mock(FireStationService.class);

    PhoneAlertService classUnderTest = new PhoneAlertService(personService, fireStationService);

    List<Address> addressList = new ArrayList<>();
    FireStation fireStation = new FireStation();
    List<Person> personList = new ArrayList<>();

    @BeforeEach
    void setUp() {


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
    }

    @Test
    void findByStation() {
        when(fireStationService.findOneStation(5)).thenReturn(fireStation);
        when(personService.findByAddressIn(fireStation.getAddress())).thenReturn(personList);
        List<Person>  listTested = classUnderTest.findByStation(5);
        assertEquals(personList, listTested);
        assertEquals(2, listTested.size());
        assertEquals("01 85 36 32 48", listTested.get(1).getPhone());

    }
}
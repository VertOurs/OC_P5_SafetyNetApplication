package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class ChildAlertServiceTest {

    PersonService personService = mock(PersonService.class);
    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    ChildAlertService classUnderTest = new ChildAlertService(personService,medicalRecordService);

    String addressString = "100, rue du port";
    Address address = new Address(addressString);
    Person person = new Person("Roger","Borgne",address,
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");
    LocalDate date = LocalDate.of(1983, 11, 20);
    Set<Medication> medicationSet = new HashSet<>();
    Set<Allergy> allergySet = new HashSet<>();
    MedicalRecord medicalRecord = new MedicalRecord(person,date,medicationSet, allergySet);
    List<Person> personList = new ArrayList<>();
    List<MedicalRecord> medicalRecordList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        personList.add(person);
        medicalRecordList.add(medicalRecord);
    }

    @Test
    void getChildrenAtThisAdress() {
        when(personService.findByAddress(addressString)).thenReturn(personList);
        when(medicalRecordService.getMedicalRecordByListOfPerson(
                personList)).thenReturn(medicalRecordList);
        classUnderTest.getChildrenAtThisAdress(addressString);
        verify(personService, times(1)).findByAddress(addressString);
        verify(medicalRecordService, times(1)).getMedicalRecordByListOfPerson(personList);

    }
}
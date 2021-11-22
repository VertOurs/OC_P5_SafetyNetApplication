package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.*;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedicalRecordServiceTest {

    MedicalRecordRepository medicalRecordRepository = mock(MedicalRecordRepository.class);
    PersonService personService = mock(PersonService.class);
    MedicationService medicationService = mock(MedicationService.class);
    AllergyService allergyService = mock(AllergyService.class);

    MedicalRecordService classUnderTest = new MedicalRecordService(
            medicalRecordRepository,
            personService,
            medicationService,
            allergyService);

    Address address = new Address("5, rue des peupliers");
    Person person = new Person("Roger","Borgne",address,
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");
    MedicalRecord medicalRecord = new MedicalRecord();
    MedicalRecordDTO dto = new MedicalRecordDTO();
    Medication medication = new Medication();
    Allergy allergy = new Allergy();
    Set<Medication> medicationSet = new HashSet<>();
    Set<Allergy> allergySet = new HashSet<>();
    List<MedicalRecord> medicalRecordList = new ArrayList<>();
    List<Person> personList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        medication.setMedication("doliprane");
        allergy.setAllergy("lessive");
        medicationSet.add(medication);
        allergySet.add(allergy);
        personList.add(person);
        medicalRecord.setPerson(person);
        medicalRecord.setBirthDate(LocalDate.of(1975, 1, 28));
        medicalRecord.setAllergies(allergySet);
        medicalRecord.setMedications(medicationSet);
        medicalRecordList.add(medicalRecord);

    }

    @Test
    void find() {
        when(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(
                person.getFirstName(), person.getLastName())).thenReturn(medicalRecord);

        classUnderTest.find("Roger", "Borgne");

        verify(medicalRecordRepository,
                times(2))
                .findOneByPerson_FirstNameAndPerson_LastName(person.getFirstName(),
                        person.getLastName());
    }

    @Test
    void getAllMedicalRecord() {
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordList);

        classUnderTest.getAllMedicalRecord();

        verify(medicalRecordRepository, times(1))
                .findAll();

    }

    @Test
    void getMedicalRecordByListOfPerson() {
        when(medicalRecordRepository.findAll()).thenReturn(medicalRecordList);
        List<MedicalRecord> mrExpected = classUnderTest.getMedicalRecordByListOfPerson(personList);
        verify(medicalRecordRepository, times(1))
                .findAll();
        assertEquals(medicalRecordList, mrExpected);
    }

    @Test
    void getOneMedicalRecordByFirstAndLastName() {
        when(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(
                person.getFirstName(), person.getLastName())).thenReturn(medicalRecord);
        classUnderTest.getOneMedicalRecordByFirstAndLastName("Roger", "Borgne");
        verify(medicalRecordRepository, times(1))
                .findOneByPerson_FirstNameAndPerson_LastName("Roger", "Borgne");
    }

    @Test
    void findMedicalRecordListByAddress() {
        when(medicalRecordRepository.findByPerson_Address_AddressName(
                address.getAddressName())).thenReturn(medicalRecordList);
        classUnderTest.findMedicalRecordListByAddress(address.getAddressName());

        verify(medicalRecordRepository, times(1))
                .findByPerson_Address_AddressName(address.getAddressName());
    }

    @Test
    void updateMedicalRecord() {
        when(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(
                person.getFirstName(), person.getLastName()))
                .thenReturn(medicalRecord);
        when(medicalRecordRepository.save(medicalRecord))
                .thenReturn(medicalRecord);


        classUnderTest.updateMedicalRecord("Roger",
                "Borgne",
                dto);

        verify(medicalRecordRepository, times(2))
                .findOneByPerson_FirstNameAndPerson_LastName("Roger", "Borgne");
        verify(medicalRecordRepository, times(1))
                .save(medicalRecord);
    }

    @Test
    void deleteOneMedicalRecord() {
        when(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(
                "Roger", "Borgne")).thenReturn(medicalRecord);
        doNothing().when(medicalRecordRepository).delete(medicalRecord);

        classUnderTest.deleteOneMedicalRecord(
                medicalRecord.getPerson().getFirstName(),
                medicalRecord.getPerson().getLastName());

        verify(medicalRecordRepository, times(1))
                .delete(medicalRecord);

    }

    @Test
    void findByPersonList() {
        when(medicalRecordRepository.findByPersonIn(personList))
                .thenReturn(medicalRecordList);
        classUnderTest.findByPersonList(personList);
        verify(medicalRecordRepository,times(1))
                .findByPersonIn(personList);
    }

    @Test
    void findMedicalRecordInListOfMR() {
        MedicalRecord mrExpected = classUnderTest.findMedicalRecordInListOfMR("Roger",
                "Borgne",
                medicalRecordList);
        assertEquals(medicalRecord, mrExpected);
    }
}
package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
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

    @BeforeEach
    void setUp() {
    }

    @Test
    void find() {
    }

    @Test
    void getAllMedicalRecord() {
    }

    @Test
    void getMedicalRecordByListOfPerson() {
    }

    @Test
    void getOneMedicalRecordByFirstAndLastName() {
    }

    @Test
    void findMedicalRecordListByAddress() {
    }

    @Test
    void save() {
    }

    @Test
    void updateMedicalRecord() {
    }

    @Test
    void deleteOneMedicalRecord() {
    }

    @Test
    void findByPersonList() {
    }

    @Test
    void findMedicalRecordInListOfMR() {
    }
}
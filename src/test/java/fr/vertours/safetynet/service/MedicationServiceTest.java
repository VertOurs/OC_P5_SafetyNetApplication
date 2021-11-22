package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.MedicationRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedicationServiceTest {

    MedicationRepository medicationRepository = mock(MedicationRepository.class);

    MedicationService classUnderTest = new MedicationService(medicationRepository);

    Person person = new Person("Roger","Borgne",
            new Address("8, rue des peupliers"),
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");

    Medication medication = new Medication();
    MedicalRecord medicalRecord = new MedicalRecord(person);

    Set<MedicalRecord> medicalRecordSet = new HashSet<>();
    Set<Medication> medicationSet = new HashSet<>();
    Set<String> setString = new HashSet<>();


    @BeforeEach
    void setUp() {
    medicalRecordSet.add(medicalRecord);

    medication.setId((long) 5);
    medication.setMedication("Méthylphénidate");
    medication.setMedicalRecord(medicalRecordSet);

    medicationSet.add(medication);
    setString.add("Méthylphénidate");


    }

    @Test
    void find() {
        when(medicationRepository.findOneByMedication("Méthylphénidate"))
                .thenReturn(medication);

        Medication medicationTested = classUnderTest.find("Méthylphénidate");

        assertEquals(medication, medicationTested);
        assertEquals(medicalRecordSet, medicationTested.getMedicalRecord());
    }

    @Test
    void save() {
        Medication doliprane = new Medication();
        when(medicationRepository.save(doliprane)).thenReturn(doliprane);
        Medication dolipraneTested = classUnderTest.save(doliprane);
        assertEquals(doliprane,dolipraneTested);
    }



    @Test
    void findOrCreateByFindProbability() {
    Medication ritaline = new Medication();
    when(classUnderTest.find("ritalmine")).thenReturn(ritaline);

    Medication ritalineTested = classUnderTest.findOrCreate("ritalmine");

    assertEquals(ritaline, ritalineTested);
    }


    @Test
    void makeStringSetFromMedication() {

        Set<String> setTested = classUnderTest.makeStringSetFromMedication(medicationSet);

        assertEquals(setString, setTested);

    }
}
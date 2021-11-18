package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.*;
import fr.vertours.safetynet.repository.AllergyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AllergyServiceTest {

    AllergyRepository allergyRepository = mock(AllergyRepository.class);

    AllergyService classUnderTest = new AllergyService(allergyRepository);

    Person person = new Person("Roger","Borgne",
            new Address("8, rue des peupliers"),
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");

    Allergy allergy = new Allergy();
    MedicalRecord medicalRecord = new MedicalRecord(person);

    Set<MedicalRecord> medicalRecordSet = new HashSet<>();
    Set<Allergy> allergySet = new HashSet<>();
    Set<String> setString = new HashSet<>();


    @BeforeEach
    void setUp() {
        medicalRecordSet.add(medicalRecord);
        allergy.setAllergy("lessive");
        allergy.setMedicalRecord(medicalRecordSet);

        allergySet.add(allergy);
        setString.add("lessive");
    }

    @Test
    void find() {
        when(allergyRepository.findOneByAllergy("lessive"))
                .thenReturn(allergy);

        Allergy allergyTested = classUnderTest.find("lessive");
        assertEquals(allergy,allergyTested);
    }

    @Test
    void save() {
        Allergy bois = new Allergy();
        when(allergyRepository.save(bois)).thenReturn(bois);
        Allergy boisTested = classUnderTest.save(bois);
        assertEquals(bois, boisTested);
    }

    @Test
    void findOrCreateByFIndProbability() {
        Allergy woodAllergy = new Allergy();
        when(classUnderTest.find("bois")).thenReturn(woodAllergy);

        Allergy allergyTested = classUnderTest.findOrCreate("bois");

        assertEquals(woodAllergy, allergyTested);
    }

    @Test
    void makeStringSetFromAllergy() {
        Set<String> setTested = classUnderTest.makeStringSetFromAllergy(allergySet);
        assertEquals(setString,setTested);
    }
}
package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MedicalRecordDTOTest {

    Address address = new Address("5, rue des peupliers");
    Person person = new Person("Roger","Borgne",address,
            "Saint-Lambert","45890","04 85 74 32 45",
            "activeService@gmail.com");
    MedicalRecord medicalRecord = new MedicalRecord();
    LocalDate date = LocalDate.of(1975, 1, 28);
    Set<Medication> medicationSet = new HashSet<>();
    Set<Allergy> allergySet = new HashSet<>();
    MedicalRecordDTO dto = new MedicalRecordDTO();
    Set<String> mediSetString = new HashSet<>();
    Set<String> alerSetString = new HashSet<>();
    @BeforeEach
    void setUp() {
        medicalRecord.setPerson(person);
        medicalRecord.setBirthDate(date);
        medicalRecord.setAllergies(allergySet);
        medicalRecord.setMedications(medicationSet);

        dto.setFirstName("Roger");
        dto.setLastName("Borgne");
        dto.setBirthdate("01/28/1975");
        dto.setMedications(mediSetString);
        dto.setAllergies(alerSetString);
    }



    @Test
    void fromMedicalRecord() {
        MedicalRecordDTO dtoExpected = MedicalRecordDTO.fromMedicalRecord(medicalRecord);
        assertEquals(dto.getFirstName(), dtoExpected.getFirstName());
        assertEquals(dto.getBirthdate(), dtoExpected.getBirthdate());
        assertEquals(dto.getAllergies(), dtoExpected.getAllergies());
    }
}
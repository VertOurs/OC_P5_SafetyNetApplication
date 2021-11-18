package fr.vertours.safetynet.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@SpringBootTest
class ChildAlertServiceTest {

    PersonService personService = mock(PersonService.class);
    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    ChildAlertService classUnderTest = new ChildAlertService(personService,medicalRecordService);

    @BeforeEach
    void setUp() {
    }

    @Test
    void getChildrenAtThisAdress() {
    }
}
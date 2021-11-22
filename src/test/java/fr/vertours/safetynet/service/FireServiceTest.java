package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FireServiceTest {

    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);

    FireService classUnderTest = new FireService(medicalRecordService);

    String addressString = "100, rue pasteur";
    List<MedicalRecord> medicalRecordlist = new ArrayList<>();

    @BeforeEach
    void setUp() {
    }

    @Test
    void getListOfPersonForOneAddressWithFireStation() {
        when(medicalRecordService.findMedicalRecordListByAddress(addressString))
                .thenReturn(medicalRecordlist);
        classUnderTest.getListOfPersonForOneAddressWithFireStation(addressString);

        verify(medicalRecordService, times(1))
                .findMedicalRecordListByAddress(addressString);
    }
}
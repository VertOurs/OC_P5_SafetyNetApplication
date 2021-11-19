package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FireServiceTest {

//    MedicalRecordService medicalRecordService = mock(MedicalRecordService.class);
//
//    FireService classUnderTest = new FireService(medicalRecordService);
//
//    String address = " 10, rue du marc";
//    FireDTO fireDTO = new FireDTO("Roger", "Borgne");
//    List<FireDTO> fireS = new ArrayList<>();
//
//
//    @Test
//    void getListOfPersonForOneAddressWithFireStation() {
//        fireS.add(fireDTO);
//        when(medicalRecordService.getListOfPersonForOneAddressWithFireStation(address)).thenReturn(fireS);
//        List<FireDTO> listTested = classUnderTest.getListOfPersonForOneAddressWithFireStation(address);
//
//        assertEquals(fireS, listTested);
//        assertEquals(fireS.get(0).getFirstName(), listTested.get(0).getFirstName());
//    }
}
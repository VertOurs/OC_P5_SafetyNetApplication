package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FloodContactDTO;
import fr.vertours.safetynet.dto.FloodDTO;
import fr.vertours.safetynet.model.*;
import org.apache.commons.collections.FastArrayList;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FloodServiceTest {

    PersonService personService = mock(PersonService.class);
    FireStationService fireStationService = mock(FireStationService.class);
    MedicalRecordService medicalRecordService = mock((MedicalRecordService.class));

    FloodService classUnderTest = new FloodService(fireStationService,medicalRecordService, personService);

    List<Integer> integers = new ArrayList<>();
    List<FireStation> fireStationList = new ArrayList<>();
    FloodDTO floodDTO = new FloodDTO();
    List<FloodDTO> floodDTOS = new ArrayList<>();
    FloodContactDTO floodContactDTO = new FloodContactDTO("Roger",
            "Borgne",
            "04 85 74 32 45",
            "20", new HashSet<>(), new HashSet<>());
    List<FloodContactDTO> contactDTOS = new ArrayList<>();


    @BeforeEach
    void setUpCOnfigTest() {
        integers.add(1);
        integers.add(2);
        contactDTOS.add(floodContactDTO);
        floodDTO.setContacts(contactDTOS);
        floodDTOS.add(floodDTO);
    }

    @Test
    void getFloodByListOfStation() {
//        FloodService instance1 = Mockito.spy(FloodService.class);
//        when(fireStationService.getListFireStationByNb(integers)).thenReturn(fireStationList);
//        Mockito.doReturn(floodDTOS).when(instance1).getListFloodDTOWithFireStationList(fireStationList);
//
//        List<FloodDTO> floodExpected =  classUnderTest.getFloodByListOfStation(integers);
//
//        assertEquals(floodDTOS, floodExpected);


    }

    @Test
    void getListFloodDTOWithFireStationListTest() {

    }
}
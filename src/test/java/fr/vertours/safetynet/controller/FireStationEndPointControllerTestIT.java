package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.service.FireStationEndPointService;
import fr.vertours.safetynet.service.FireStationService;
import fr.vertours.safetynet.service.MedicalRecordService;
import fr.vertours.safetynet.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationEndPointControllerTestIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FireStationEndPointService fireStationEndPointService;




    @Test
    void getPersonFromFireStationWithCount() throws Exception {
        FireStationInfoDTO fireStationInfoDTO = fireStationEndPointService.getFireStationInfoDTOfromStationNumber(1);
        mockMvc.perform(get("/firestation?stationNumber=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.personList[0].firstName", is(fireStationInfoDTO.getPersonList().get(0).getFirstName())));
    }
}
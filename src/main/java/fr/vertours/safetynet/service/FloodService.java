package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.dto.FloodContactDTO;
import fr.vertours.safetynet.dto.FloodDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FloodService implements IFloodService {

    public FloodService() {
    }

    public FloodService(FireStationService fireStationService, MedicalRecordService medicalRecordService, PersonService personService) {
        this.fireStationService = fireStationService;
        this.medicalRecordService = medicalRecordService;
        this.personService = personService;
    }

    @Autowired
    FireStationService fireStationService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    PersonService personService;

    /**
     * converts a list of integers representing the station numbers into a list of FloodDTO.
     * @param stationList
     * @return a list of FloodDTO.
     */
    @Override
    public List<FloodDTO> getFloodByListOfStation(List<Integer> stationList) {

        List<FireStation> fireStationList = fireStationService.getListFireStationByNb(stationList);
        List<FloodDTO> floodDTOList = getListFloodDTOWithFireStationList(fireStationList);
        return floodDTOList;

    }

    /**
     * Convert a list of FireStation in List of FloodDTO
     * @param fireStationList
     * @return List of FloodDTO.
     */
    public List<FloodDTO> getListFloodDTOWithFireStationList(List<FireStation> fireStationList) {
        List<FloodDTO> floodDTOList = new ArrayList<>();
        for (FireStation f: fireStationList) {
            for (Address a : f.getAddress()) {
                List<Person> personList = personService.findListOfPersonByAddress(a) ;
                List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecordByListOfPerson(personList);
                List<FloodContactDTO> floodContactDTOList = FloodContactDTO.fromListPersonMr(personList,medicalRecordList);
                FloodDTO floodDTO = new FloodDTO();
                floodDTO.setStation(f.getStation());
                floodDTO.setAddress(a.getAddressName());
                floodDTO.setContacts(floodContactDTOList);
                floodDTOList.add(floodDTO);
            }
        }
        return floodDTOList;
    }
}

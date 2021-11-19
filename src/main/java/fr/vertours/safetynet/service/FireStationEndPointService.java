package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.dto.PersonForFireInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.vertours.safetynet.util.CustomTools.calculateAgewithLocalDate;

@Service
public class FireStationEndPointService implements IFireStationEndPointService {

    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    PersonService personService;
    @Autowired
    FireStationService fireStationService;



    @Override
    public FireStationInfoDTO getFireStationInfoDTOfromStationNumber(int stationNumber) {
         List<Person> personList = findByStation(stationNumber);
        List<PersonForFireInfoDTO> infoDTOS = personFromFireStation(personList);
        return getFireStationInfoDTOFromList(infoDTOS, personList);

    }

    private List<PersonForFireInfoDTO> personFromFireStation(List<Person> personList) {
        List<PersonForFireInfoDTO> infoDTOS = personList.stream().map(PersonForFireInfoDTO::fromPerson).collect(Collectors.toList());
        return infoDTOS;
    }

    private FireStationInfoDTO getFireStationInfoDTOFromList (List<PersonForFireInfoDTO> personInfoList, List<Person> personList) {

        List<MedicalRecord> mRList = medicalRecordService.getMedicalRecordByListOfPerson(personList);
        int nbAdultes = (int) mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) >= 18).count();
        int nbEnfants = (int) mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) < 18).count();
        FireStationInfoDTO fireStationInfoDTO = new FireStationInfoDTO(personInfoList, nbEnfants, nbAdultes);
        return fireStationInfoDTO;
    }
    /**
     * find a list of people in dataBase with a FireStation number.
     * @param station
     * @return a list of Person entity.
     */
    private List<Person> findByStation(int station) {
        FireStation fireStation = fireStationService.findOneStation(station);
        Set<Address> addressSet = fireStation.getAddress();
        return  personService.findByAddressIn(addressSet);


    }
}

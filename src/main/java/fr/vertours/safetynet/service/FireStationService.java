package fr.vertours.safetynet.service;
import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.dto.PersonForFireInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.model.exceptions.FireStationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import fr.vertours.safetynet.repository.FireStationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FireStationService.class);

    private final FireStationRepository fireStationRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    PersonService personService;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void saveOneStation(FireStationDTO fireStationDTO) {
        Set<Address> addressSet = fireStationDTO.getAddress().stream().map(address -> addressService.findOrCreate(address)).collect(Collectors.toSet());
        FireStation fireStation = fireStationDTO.createFireStation();
        fireStation.setAddress(addressSet);
        fireStation.setStation(fireStationDTO.getStation());
        fireStationRepository.save(fireStation);
    }
    public void saveAllStations(Collection<FireStation> fireStationCollection){
        fireStationRepository.saveAll(fireStationCollection);
    }

    public List<FireStation> getListOfAllStations(){
       return fireStationRepository.findAll();
    }

    public FireStation findOneStation(int fireStation) {
        return fireStationRepository.findByStation(fireStation);
    }
    public Collection<FireStation> findOneStationByAddress(Address address) {
        return fireStationRepository.findFireStationByAddress(address);
    }

    /**
     *delete a FireStation.
     * @param firestation
     */
    public void deleteOneFireStation(int firestation) {
        LOGGER.info("call : deleteOneFireStation method");
        Optional<FireStation> existingFireStation = Optional.ofNullable(fireStationRepository.findByStation(firestation));
        if(existingFireStation.isEmpty()) {
            throw new FireStationNotFoundException(firestation);
        }
        FireStation fireStationObject = fireStationRepository.findByStation(firestation);
        fireStationRepository.delete(fireStationObject);
    }

    public void updateStationForOneAddress(int station, Address address) {
        FireStation fireStation = fireStationRepository.findByStation(station);
        Address addressObject = addressService.findOrCreate(address.getAddressName());
        fireStation.addAdress(addressObject);
        fireStationRepository.save(fireStation);
    }

    public List<FireStation> getListFireStationByNb(List<Integer> stations) {
        return fireStationRepository.findByStationIn(stations);
    }
    public List<Person> findByStation(int station) {
        return personService.findByStation(station);
    }

    public List<PersonForFireInfoDTO> personFromFireStation(List<Person> personList) {
        return  personService.personFromFireStation(personList);
    }
    public FireStationInfoDTO getFireStationInfoDTOFromList( List<PersonForFireInfoDTO> personInfoList, List<Person> personList) {
        return personService.getFireStationInfoDTOFromList(personInfoList,personList);
    }







    
}
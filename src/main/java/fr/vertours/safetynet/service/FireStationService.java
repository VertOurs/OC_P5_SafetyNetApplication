package fr.vertours.safetynet.service;
import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.dto.PersonForFireInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.model.exceptions.EmptyDBException;
import fr.vertours.safetynet.model.exceptions.FireStationAlreadyPresentException;
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



    private final FireStationRepository fireStationRepository;
    private final AddressService addressService;

    public FireStationService(FireStationRepository fireStationRepository, AddressService addressService) {
        this.fireStationRepository = fireStationRepository;
        this.addressService = addressService;
    }




    /**
     *  save a FireStation.
     * @param fireStationDTO
     */
    public void saveOneStation(FireStationDTO fireStationDTO) {
        Optional<FireStation> existingFireStation =
                Optional.ofNullable(
                        fireStationRepository.findByStation(
                                fireStationDTO.getStation()));
        if(existingFireStation.isPresent()) {
            throw new FireStationAlreadyPresentException(fireStationDTO.getStation());
        }

        Set<Address> addressSet = fireStationDTO.getAddress().stream().map(address -> addressService.findOrCreate(address)).collect(Collectors.toSet());
        FireStation fireStation = fireStationDTO.createFireStation();
        fireStation.setAddress(addressSet);
        fireStation.setStation(fireStationDTO.getStation());
        fireStationRepository.save(fireStation);
    }
    public void saveAllStations(Collection<FireStation> fireStationCollection){
        fireStationRepository.saveAll(fireStationCollection);
    }

    /**
     * finds all FireStations.
     * @return a list of FireStationEntity.
     */
    public List<FireStation> getListOfAllStations(){
       List<FireStation> fireStationList = fireStationRepository.findAll();
       if(fireStationList.isEmpty()) {
           throw new EmptyDBException();
       }
       return fireStationList;
    }

    /**
     * find a FireStation in Database
     * @param fireStation
     * @return a FireStation entity.
     */
    public FireStation findOneStation(int fireStation) {
        Optional<FireStation> existingFireStation = Optional.ofNullable(fireStationRepository.findByStation(fireStation));
        if(existingFireStation.isEmpty()) {
            throw new FireStationNotFoundException(fireStation);
        }
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

        Optional<FireStation> existingFireStation = Optional.ofNullable(fireStationRepository.findByStation(firestation));
        if(existingFireStation.isEmpty()) {
            throw new FireStationNotFoundException(firestation);
        }
        FireStation fireStationObject = fireStationRepository.findByStation(firestation);
        fireStationRepository.delete(fireStationObject);
    }

    /**
     * update a Firestation
     * @param station
     * @param address
     */
    public void updateStationForOneAddress(int station, Address address) {
        Optional<FireStation> existingFireStation = Optional.ofNullable(fireStationRepository.findByStation(station));
        if(existingFireStation.isEmpty()) {
            throw new FireStationNotFoundException(station);
        }
        FireStation fireStation = fireStationRepository.findByStation(station);
        Address addressObject = addressService.findOrCreate(address.getAddressName());
        fireStation.addAdress(addressObject);
        fireStationRepository.save(fireStation);
    }

    /**
     * find list of FireStation.
     * @param stations
     * @return a list of FireStation entity.
     */
    public List<FireStation> getListFireStationByNb(List<Integer> stations) {
        List<FireStation> fireStationList = fireStationRepository.findByStationIn(stations);
        if(fireStationList.isEmpty()) {
            throw new EmptyDBException();
        }
        return fireStationList;
    }









    
}
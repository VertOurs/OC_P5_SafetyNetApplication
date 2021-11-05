package fr.vertours.safetynet.service;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import fr.vertours.safetynet.repository.FireStationRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    @Autowired
    AddressService addressService;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void saveOneStation(FireStationDTO fireStationDTO) {
        Set<Address> addressSet = fireStationDTO.getAddress().stream().map(address -> addressService.findOrCreate(address)).collect(Collectors.toSet());
        FireStation fireStation = fireStationDTO.createFireStation();
        fireStation.setAddress(addressSet);
        //rajout de cette ligne pour eviter nullpointexception sans succès
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

    public void deleteOneFireStation(int firestation) {
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







    
}
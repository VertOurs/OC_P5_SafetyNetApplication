package fr.vertours.safetynet.service;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import fr.vertours.safetynet.repository.FireStationRepository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    @Autowired
    AddressService addressService;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void saveOneStation(FireStationDTO fireStationDTO) {
        FireStation fireStation = fireStationDTO.createFireStation();
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

    public void deleteOneFireStation(int firestation) {
        FireStation fireStationObject = fireStationRepository.findByStation(firestation);
        fireStationRepository.delete(fireStationObject);
    }
    //erreur 500
    public void updateStationForOneAddress(int station, String address) {
        FireStation fireStation = fireStationRepository.findByStation(station);
        Address addressObject = addressService.save(address);
        for(Address a : fireStation.getAddress()) {
            if(a.getAddressName() != null && a.getAddressName().length() > 0 && !Objects.equals(a.getAddressName(), address)) {
            fireStation.addAdress(addressObject);
        }
        }
    }








    
}
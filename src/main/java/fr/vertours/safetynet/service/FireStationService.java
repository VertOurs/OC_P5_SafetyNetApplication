package fr.vertours.safetynet.service;
import fr.vertours.safetynet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import fr.vertours.safetynet.repository.FireStationRepository;

import java.util.Collection;
import java.util.List;

@Service
public class FireStationService {

    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }


    public void save(FireStation fireStation) {
        fireStationRepository.save(fireStation);
    }

    public List<FireStation> findAll(){
       return fireStationRepository.findAll();
    }

    public FireStation findByStation(int fireStation) {
        return fireStationRepository.findByStation(fireStation);
    }

    public void saveAll(Collection<FireStation> fireStationCollection){
        fireStationRepository.saveAll(fireStationCollection);
    }




    
}
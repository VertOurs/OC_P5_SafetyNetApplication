package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.repository.FireStationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class FireStationService {
    private final FireStationRepository fireStationRepository;

    public FireStationService(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }

    @PostMapping("/firestation")
    public void create(@RequestBody FireStation fireStation) {
        this.fireStationRepository.save(fireStation);
    }
    @GetMapping("/firestation")
    public List<FireStation> getListFireStation(){
        return this.fireStationRepository.findAll();
    }
    @DeleteMapping ("/firestation")
    public void deleteStation(){
        this.fireStationRepository.deleteAll();
    }
    @PutMapping ("/firestation")
    public void putStation(@PathVariable int station){
        this.fireStationRepository.updateStation(station);
    }
}

package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.repository.FireStationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {
    private final FireStationRepository fireStationRepository;

    public FireStationController(FireStationRepository fireStationRepository) {
        this.fireStationRepository = fireStationRepository;
    }
    @PostMapping ("/firestations")
    public void create(@RequestBody FireStation fireStation) {
        this.fireStationRepository.save(fireStation);
    }
    @GetMapping ("/firestations")
    public List<FireStation> getListFireStation(){
        return this.fireStationRepository.findAll();
    }

    @GetMapping ("/nbfirestation/{station}")
    public FireStation getStation(@PathVariable int station){
        return this.fireStationRepository.findByStation(station);
    }
}

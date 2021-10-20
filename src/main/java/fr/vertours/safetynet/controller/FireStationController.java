package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (path = "/firestation")
public class FireStationController {

    private final  FireStationService firestationService;

    public FireStationController(FireStationService fireStationService) {
        this.firestationService = fireStationService;
    }


    @GetMapping
    public List<FireStation> getListFireStation(){
        return this.firestationService.getListOfAllStations();
    }

    @GetMapping ("/{station}")
    public FireStation getStation(@PathVariable int station){
        return this.firestationService.findOneStation(station);
    }

    @PostMapping
    public void create(@RequestBody FireStationDTO fireStation) {
        this.firestationService.saveOneStation(fireStation);
    }
    //erreur 500
    @PutMapping("/{station}")
    public void updateNbStationForOneAddress(@PathVariable int station, @RequestBody String address) {
         this.firestationService.updateStationForOneAddress(station, address);
    }

    //erreur 500
    @DeleteMapping(path = "{nbStation}")
    public void deleteOneStation(@PathVariable int nbStation) {
        this.firestationService.deleteOneFireStation(nbStation);
    }
}

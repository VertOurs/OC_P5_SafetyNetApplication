package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping (path = "/firestation")
public class FireStationController {

    private final  FireStationService firestationService;

    public FireStationController(FireStationService fireStationService) {
        this.firestationService = fireStationService;
    }


    @GetMapping
    public List<FireStationDTO> getListFireStation(){
        List<FireStation> fireStationList = this.firestationService.getListOfAllStations();
        List<FireStationDTO> fireStationDTOList = fireStationList.stream().map(FireStationDTO::fromFireStation).collect(Collectors.toList());
        return fireStationDTOList;
    }

    @GetMapping ("/{station}")
    public FireStationDTO getStation(@PathVariable int station){
        FireStation fireStation = this.firestationService.findOneStation(station);
        return FireStationDTO.fromFireStation(fireStation);
    }

    @PostMapping
    public void create(@RequestBody FireStationDTO fireStation) {
        this.firestationService.saveOneStation(fireStation);
    }

    @PutMapping("/{station}")
    public void updateNbStationForOneAddress(@PathVariable int station, @RequestBody Address address) {
         this.firestationService.updateStationForOneAddress(station, address);
    }


    @DeleteMapping(path = "{nbStation}")
    public void deleteOneStation(@PathVariable int nbStation) {
        this.firestationService.deleteOneFireStation(nbStation);
    }
}

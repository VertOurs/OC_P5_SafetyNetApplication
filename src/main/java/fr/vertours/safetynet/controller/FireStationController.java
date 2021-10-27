package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FireStationController {

    private final  FireStationService firestationService;

    public FireStationController(FireStationService fireStationService) {
        this.firestationService = fireStationService;
    }




    @GetMapping("/firestation/all")
    public List<FireStationDTO> getListFireStation(){
        List<FireStation> fireStationList = this.firestationService.
                getListOfAllStations();

        List<FireStationDTO> fireStationDTOList = fireStationList.stream()
                .map(FireStationDTO::fromFireStation)
                .collect(Collectors.toList());

        return fireStationDTOList;
    }

    @GetMapping ("/firestation/{station}")
    public FireStationDTO getStation(@PathVariable int station){
        FireStation fireStation = this.firestationService.findOneStation(station);
        return FireStationDTO.fromFireStation(fireStation);
    }

    @PostMapping("/firestation")
    public void create(@RequestBody FireStationDTO fireStationDTO) {
        this.firestationService.saveOneStation(fireStationDTO);
    }

    @PutMapping("/firestation/{station}")
    public void updateNbStationForOneAddress(@PathVariable int station,
                                             @RequestBody Address address) {
         this.firestationService.updateStationForOneAddress(station, address);
    }


    @DeleteMapping(path = "/firestation/{nbStation}")
    public void deleteOneStation(@PathVariable int nbStation) {
        this.firestationService.deleteOneFireStation(nbStation);
    }
}

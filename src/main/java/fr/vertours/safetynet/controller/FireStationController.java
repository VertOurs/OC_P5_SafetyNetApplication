package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.AllInfoPersonDTO;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.dto.PersonForFireInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.FireStationService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<FireStationDTO>> getListFireStation(){
        List<FireStation> fireStationList = this.firestationService.
                getListOfAllStations();

        List<FireStationDTO> fireStationDTOList = fireStationList.stream()
                .map(FireStationDTO::fromFireStation)
                .collect(Collectors.toList());

        return ResponseEntity.accepted().body(fireStationDTOList);
    }

    @GetMapping ("/firestation/{station}")
    public ResponseEntity<FireStationDTO> getStation(@PathVariable int station){
        FireStation fireStation = this.firestationService.findOneStation(station);
        return ResponseEntity.accepted().body(FireStationDTO.fromFireStation(fireStation));
    }

    @GetMapping("/firestation")
    public ResponseEntity<FireStationInfoDTO> getPersonFromFireStationWithCount(@RequestParam ("stationNumber") int station) {
        List<Person> personList = firestationService.findByStation(station);
        List<PersonForFireInfoDTO> personInfoList = firestationService.personFromFireStation(personList);
        return ResponseEntity.accepted().body(firestationService.getFireStationInfoDTOFromList(personInfoList,personList));
    }

    @PostMapping("/firestation")
    public ResponseEntity<String> create(@RequestBody FireStationDTO fireStationDTO) {
        this.firestationService.saveOneStation(fireStationDTO);
        return ResponseEntity.accepted().body("La station à bien été créé.");
    }

    @PutMapping("/firestation/{station}")
    public ResponseEntity<String> updateNbStationForOneAddress(@PathVariable int station,
                                             @RequestBody Address address) {
         this.firestationService.updateStationForOneAddress(station, address);
         return ResponseEntity.accepted().body("la station à bien été modifiée.");
    }


    @DeleteMapping(path = "/firestation/{nbStation}")
    public ResponseEntity<String> deleteOneStation(@PathVariable int nbStation) {
        this.firestationService.deleteOneFireStation(nbStation);
        return ResponseEntity.accepted().body("la station à bien été supprimer.");
    }
}

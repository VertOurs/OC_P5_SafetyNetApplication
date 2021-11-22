package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.dto.PersonForFireInfoDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.FireStationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FireStationController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FireStationController.class);

    private final  FireStationService firestationService;

    public FireStationController(FireStationService fireStationService) {
        this.firestationService = fireStationService;
    }

    /**
     * Endpoint that allows you to finds all FireStations in a database.
     * @return a list of FireStationDTO entity with ResponseEntity.
     */
    @GetMapping("/firestation/all")
    public ResponseEntity<List<FireStationDTO>> getListFireStation(){
        LOGGER.debug("call endpoint /firestation/all");
        List<FireStation> fireStationList = this.firestationService.
                getListOfAllStations();

        List<FireStationDTO> fireStationDTOList = fireStationList.stream()
                .map(FireStationDTO::fromFireStation)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(fireStationDTOList);
    }

    /**
     * Endpoint that allows you to find a FireStation a database.
     * @param station
     * @return a FireStationDTO with ResponseEntity.
     */
    @GetMapping ("/firestation/{station}")
    public ResponseEntity<FireStationDTO> getStation(@PathVariable int station){
        LOGGER.debug("call endpoint  /firestation");
        FireStation fireStation = this.firestationService.findOneStation(station);
        return ResponseEntity.ok().body(FireStationDTO.fromFireStation(fireStation));
    }



    /**
     * Endpoint that allows you to backup a Firestation in a database.
     * @param fireStationDTO
     * @return a success message with ResponseEntity.
     */
    @PostMapping("/firestation")
    public ResponseEntity<String> create(@RequestBody FireStationDTO fireStationDTO) {
        this.firestationService.saveOneStation(fireStationDTO);
        LOGGER.debug("call endpoint POST /firestation");
        return ResponseEntity.ok().body("the FireStation has been created in the database.");
    }

    /**
     * Endpoint that allows you to modify a Firestation in a database.
     * @param station
     * @param address
     * @return a success message with ResponseEntity.
     */
    @PutMapping("/firestation/{station}")
    public ResponseEntity<String> updateNbStationForOneAddress(@PathVariable int station,
                                             @RequestBody Address address) {
        LOGGER.debug("call endpoint PUT /firestation");
        this.firestationService.updateStationForOneAddress(station, address);

        return ResponseEntity.ok().body("la station à bien été modifiée.");
    }

    /**
     * Endpoint that allows you to delete a FireStation from the database.
     * @param nbStation
     * @return a success message with ResponseEntity.
     */
    @DeleteMapping(path = "/firestation/{nbStation}")
    public ResponseEntity<String> deleteOneStation(@PathVariable int nbStation) {
        LOGGER.info("call endpoint DEL /firestation");
        this.firestationService.deleteOneFireStation(nbStation);

        return ResponseEntity.ok().body("the FireStation has been deleted from the database.");
    }
}

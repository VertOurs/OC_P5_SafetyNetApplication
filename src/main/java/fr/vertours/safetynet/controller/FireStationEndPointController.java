package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireStationInfoDTO;
import fr.vertours.safetynet.service.IFireStationEndPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FireStationEndPointController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FireStationEndPointController.class);

    @Autowired
    IFireStationEndPointService service;

    /**
     * Endpoint that allows you to find a list of people in a database.
     * @param station
     * @return a list of FireStationInfoDTO entity with ResponseEntity.
     */
    @GetMapping("/firestation")
    public ResponseEntity<FireStationInfoDTO> getPersonFromFireStationWithCount(@RequestParam("stationNumber") int station) {
        LOGGER.debug("call endpoint  /firestation");
        return ResponseEntity.ok().body(service.getFireStationInfoDTOfromStationNumber(station));
    }
}

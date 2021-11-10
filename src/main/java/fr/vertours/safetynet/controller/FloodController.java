package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.service.IFloodService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {

    private final static Logger LOGGER = LogManager.getLogger(FloodController.class);

    @Autowired
    IFloodService iFloodService;

    @GetMapping("/flood/stations")
    public ResponseEntity<List<?>> endPoint5Flood(@RequestParam("stations") List<Integer> stationList) {
        LOGGER.info("call endpoint /flood/stations");
        return ResponseEntity.accepted().body(iFloodService.getFloodByListOfStation(stationList));

    }
}

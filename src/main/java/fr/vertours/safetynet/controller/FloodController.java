package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.service.IFloodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {

    private final static Logger LOGGER = LoggerFactory.getLogger(FloodController.class);

    @Autowired
    IFloodService iFloodService;

    @GetMapping("/flood/stations")
    public ResponseEntity<List<?>> endPoint5Flood(@RequestParam("stations") List<Integer> stationList) {
        LOGGER.info("call endpoint /flood/stations");
        return ResponseEntity.accepted().body(iFloodService.getFloodByListOfStation(stationList));

    }
}

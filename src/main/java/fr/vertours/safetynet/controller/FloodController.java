package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.service.IFloodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {

    @Autowired
    IFloodService iFloodService;

    @GetMapping("/flood/stations")
    public List<?> endPoint5Flood(@RequestParam("stations") List<Integer> stationList) {
        return iFloodService.getFloodByListOfStation(stationList);

    }
}

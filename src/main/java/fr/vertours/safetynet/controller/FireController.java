package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireDTO;
import fr.vertours.safetynet.service.IFireService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireController {

    private final static Logger LOGGER = LogManager.getLogger(FireController.class);

    @Autowired
    IFireService ifireService;

    @GetMapping("/fire")
    public ResponseEntity<List<FireDTO>> getListOfPersonForOneAddressWithFireStation(@RequestParam("address") String address) {
        LOGGER.info("call endpoint /fire");
        return ResponseEntity.accepted().body(this.ifireService.getListOfPersonForOneAddressWithFireStation(address));

    }
}

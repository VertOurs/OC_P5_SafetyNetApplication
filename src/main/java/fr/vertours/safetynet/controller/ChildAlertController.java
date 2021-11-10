package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.ChildAlertDTO;
import fr.vertours.safetynet.service.IChildAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildAlertController {

    private final static Logger LOGGER = LogManager.getLogger(ChildAlertController.class);

    @Autowired
    IChildAlertService iChildAlertService;


    @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertDTO> getListOfChildrenAtThisAddress(
            @RequestParam("address") String address) {
        LOGGER.info("call endpoint /childAlert");
        return ResponseEntity.accepted().body(iChildAlertService.getChildrenAtThisAdress(address));
    }
}

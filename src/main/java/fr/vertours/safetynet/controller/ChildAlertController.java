package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.ChildAlertDTO;
import fr.vertours.safetynet.service.IChildAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildAlertController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChildAlertController.class);

    @Autowired
    IChildAlertService iChildAlertService;

    /**
     * Endpoint that returns a ChildAlertDTO according to the project requirements.
     * @param address
     * @return ChildAlertDTO with ResponseEntity.
     */
    @GetMapping("/childAlert")
    public ResponseEntity<ChildAlertDTO> getListOfChildrenAtThisAddress(
            @RequestParam("address") String address) {
        LOGGER.info("call endpoint /childAlert");
        return ResponseEntity.ok().body(iChildAlertService.getChildrenAtThisAdress(address));
    }
}

package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.ChildAlertDTO;
import fr.vertours.safetynet.service.IChildAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChildAlertController {

    @Autowired
    IChildAlertService iChildAlertService;


    @GetMapping("/childAlert")
    public ChildAlertDTO getListOfChildrenAtThisAddress(@RequestParam("address") String address) {
        return iChildAlertService.getChildrenAtThisAdress(address);
    }
}

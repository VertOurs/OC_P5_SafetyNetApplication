package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.FireDTO;
import fr.vertours.safetynet.service.IFireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FireController {

    @Autowired
    IFireService ifireService;

    @GetMapping("/fire")
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(@RequestParam("address") String address) {
        return this.ifireService.getListOfPersonForOneAddressWithFireStation(address);

    }
}

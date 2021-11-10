package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IPhoneAlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhoneAlertController {

    Logger LOGGER = LogManager.getLogger(PhoneAlertController.class);

    @Autowired
    IPhoneAlertService iPhoneAlertService;

    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getListPhoneNumberByFireStation(@RequestParam("firestation") int station) {
        List<Person> personList = this.iPhoneAlertService.findByStation(station);
        LOGGER.info("call endpoint /phoneAlert");
        return ResponseEntity.accepted().body(personList.stream().map(Person::getPhone).collect(Collectors.toList()));
    }
}

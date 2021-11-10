package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IEmailService;
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
public class CommunityEmailController {

    Logger LOGGER = LogManager.getLogger(CommunityEmailController.class);

    @Autowired
    IEmailService iEmailService;

    @GetMapping("/communityEmail")
    public ResponseEntity<List<String>> getAllEmailForOneCity(
            @RequestParam("city") String city) {
        List<Person> personList = this.iEmailService.findByCity(city);
        LOGGER.info("call endpoint /communityEmail");
        return ResponseEntity.accepted().body(personList.stream().
                map(Person::getEmail).collect(Collectors.toList()));
    }
}

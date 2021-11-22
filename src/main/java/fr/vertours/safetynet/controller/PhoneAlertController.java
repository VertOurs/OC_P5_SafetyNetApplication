package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IPhoneAlertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhoneAlertController {

   private final static Logger LOGGER = LoggerFactory.getLogger(PhoneAlertController.class);

    @Autowired
    IPhoneAlertService iPhoneAlertService;

    /**
     *Endpoint that allow you to find a list of person in accordance with the project requirements.
     * @param station
     * @return a list of String with ResponseEntity.
     */
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<String>> getListPhoneNumberByFireStation(@RequestParam("firestation") int station) {
        LOGGER.info("call endpoint /phoneAlert");
        List<Person> personList = this.iPhoneAlertService.findByStation(station);
        return ResponseEntity.ok().body(personList.stream().map(Person::getPhone).collect(Collectors.toList()));
    }
}

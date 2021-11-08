package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IPhoneAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PhoneAlertController {

    @Autowired
    IPhoneAlertService iPhoneAlertService;

    @GetMapping("/phoneAlert")
    public List<String> getListPhoneNumberByFireStation(@RequestParam("firestation") int station) {
        List<Person> personList = this.iPhoneAlertService.findByStation(station);
        return  personList.stream().map(Person::getPhone).collect(Collectors.toList());
    }
}

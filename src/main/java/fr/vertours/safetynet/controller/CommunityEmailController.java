package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommunityEmailController {

    @Autowired
    IEmailService iEmailService;

    @GetMapping("/communityEmail")
    public List<String> getAllEmailForOneCity(
            @RequestParam("city") String city) {
        List<Person> personList = this.iEmailService.findByCity(city);
        return personList.stream().
                map(Person::getEmail).collect(Collectors.toList());
    }
}

package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @GetMapping
    public List<Person> getPersons() {
        return personService.getAllPersons();
    }

    @PostMapping
    public void registerNewPerson(@RequestBody PersonDTO person) {
        personService.addPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long personId){
        personService.deletePerson(personId);
    }
}

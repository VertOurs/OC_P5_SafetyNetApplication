package fr.vertours.safetynet.controller;

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
        //List<Person> personList = personService.getAllPersons();
        //return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @PostMapping
    public void registerNewPerson(@RequestBody Person person) {
        personService.addPerson(person);
    }

    @DeleteMapping(path = "{personId}")
    public void deletePerson(@PathVariable("personId") Long personId){
        personService.deletePerson(personId);
    }
}

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
    public List<Person> getListOfPersons() {
        return personService.getAllPersons();
    }

    @GetMapping(path = "{personID}")
    public Person getOnePerson(@PathVariable ("personID") Long personID){
        return personService.getOnePersonByID(personID);
    }


    @PostMapping
    public void registerNewPerson(@RequestBody PersonDTO person) {
        personService.addPerson(person);
    }

    @PutMapping(path = "{lastName}-{firstName}")
    public void updatePerson(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName, @RequestParam(required = false) String address,@RequestParam(required = false) String city, @RequestParam(required = false) String zip, @RequestParam(required = false) String phone, @RequestParam(required = false) String email) {
        personService.updatePerson(lastName, firstName, address, city, zip, phone, email);
    }

    // Erreur 500
    @DeleteMapping(path = "{lastName}/{firstName}")
    public void deletePerson(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName){
        personService.deletePerson(lastName, firstName);
    }
}

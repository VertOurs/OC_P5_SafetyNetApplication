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

    @PutMapping(path = "{firstName}/{lastName}")
    public void updatePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @RequestParam(required = false) String address,@RequestParam(required = false) String city, @RequestParam(required = false) String zip, @RequestParam(required = false) String phone, @RequestParam(required = false) String email) {
        personService.updatePerson(firstName, lastName, address, city, zip, phone, email);
    }


    @DeleteMapping(path = "{firstName}/{lastName}")
    public void deletePerson(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName){
        personService.deletePerson(firstName, lastName);
    }
}

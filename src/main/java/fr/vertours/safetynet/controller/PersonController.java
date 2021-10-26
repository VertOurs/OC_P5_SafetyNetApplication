package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/person/all")
    public List<PersonDTO> getListOfPersons() {
        List<Person> personList = this.personService.getAllPersons();
        List<PersonDTO> personDTOList = personList.stream()
                .map(PersonDTO::fromPerson)
                .collect(Collectors.toList());
        return personDTOList;
    }

    @GetMapping(path = "/person/{firstName}/{lastName}")
    public PersonDTO getOnePerson(@PathVariable ("firstName") String firstName,
                                  @PathVariable ("lastName") String lastName) {
        Person person = personService.find(firstName, lastName);
        PersonDTO personDTO = new PersonDTO(person.getFirstName(),
                person.getLastName(),
                person.getAddress().getAddressName(),
                person.getCity(),
                person.getZip(),
                person.getPhone(),
                person.getEmail());
        return personDTO;
    }

    @PostMapping("/person")
    public void registerNewPerson(@RequestBody PersonDTO personDTO) {
        personService.addPerson(personDTO);
    }
    // visiblement il y a un probleme (cascade)                                            !!*************!!
    @PutMapping(path = "/person/{firstName}/{lastName}")
    public void updatePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName,
                             @RequestBody PersonDTO personDTO ) {
        personService.updatePerson(firstName, lastName, personDTO);
    }


    @DeleteMapping(path = "/person/{firstName}/{lastName}")
    public void deletePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
    }
}

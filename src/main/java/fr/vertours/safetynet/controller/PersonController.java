package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.*;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/person/all")
    public ResponseEntity<List<PersonDTO>> getListOfPersons() throws NullPointerException {
        List<Person> personList = this.personService.getAllPersons();
        LOGGER.info("call endPoint person/all");

        //try {
           List<PersonDTO> personDTOList = personList.stream()
                    .map(PersonDTO::fromPerson)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTOList);

       /* } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }*/
    }

    @GetMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<PersonDTO> getOnePerson(@PathVariable ("firstName") String firstName,
                                  @PathVariable ("lastName") String lastName) {
        Person person = personService.find(firstName, lastName);
        PersonDTO personDTO = new PersonDTO(person.getFirstName(),
                person.getLastName(),
                person.getAddress().getAddressName(),
                person.getCity(),
                person.getZip(),
                person.getPhone(),
                person.getEmail());
        LOGGER.info("call endPoint GET /person/");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTO);
    }

    @PostMapping("/person")
    public ResponseEntity<String> registerNewPerson(@RequestBody PersonDTO personDTO) {
        LOGGER.info("call endPoint POST/person");
        personService.addPerson(personDTO);
        LOGGER.info("call endPoint POST /person");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTO.getFirstName() + " " + personDTO.getFirstName() + " à bien été crée en DB.");
    }

    @PutMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> updatePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName,
                             @RequestBody PersonDTO personDTO ) {
        personService.updatePerson(firstName, lastName, personDTO);
        LOGGER.info("call endPoint PUT /person");
        return ResponseEntity.accepted().body("les Modifications de " + firstName + " " + lastName + " ont bien été prise en compte dans la DB.");
    }

    @DeleteMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
        LOGGER.info("call endPoint DEL /person");
        return ResponseEntity.accepted().body(firstName + " " + lastName + " a bien été supprimer de la DB.");
    }
}

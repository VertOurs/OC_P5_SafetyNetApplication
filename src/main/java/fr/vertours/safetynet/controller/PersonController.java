package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.controller.exceptions.RestResponseEntityExceptionHandler;
import fr.vertours.safetynet.dto.*;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.MedicalRecordService;
import fr.vertours.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    Logger LOGGER = LogManager.getLogger(PersonController.class);

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/person/all")
    public ResponseEntity<List<PersonDTO>> getListOfPersons() throws RestResponseEntityExceptionHandler {
        List<Person> personList = this.personService.getAllPersons();
        LOGGER.info("call endPoint person/all");
        try {
            List<PersonDTO> personDTOList = personList.stream()
                    .map(PersonDTO::fromPerson)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTOList);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
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
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTO);
    }

    @PostMapping("/person")
    public ResponseEntity<String> registerNewPerson(@RequestBody PersonDTO personDTO) {
        LOGGER.info("call endPoint POST/person");
        personService.addPerson(personDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTO.getFirstName() + " " + personDTO.getFirstName() + " à bien été crée en DB.");
    }

    @PutMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> updatePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName,
                             @RequestBody PersonDTO personDTO ) {
        personService.updatePerson(firstName, lastName, personDTO);
        return ResponseEntity.accepted().body("les Modifications de " + firstName + " " + lastName + " ont bien été prise en compte dans la DB.");
    }

    @DeleteMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);

        return ResponseEntity.accepted().body(firstName + " " + lastName + " a bien été supprimer de la DB.");
    }
}

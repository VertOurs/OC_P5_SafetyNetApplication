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

    /**
     * EndPoint that returns the list of all persons present in the database.
     * @return a list of <PersonDTO> entities with ResponseEntity.
     */
    @GetMapping("/person/all")
    public ResponseEntity<List<PersonDTO>> getListOfPersons()  {
        LOGGER.info("Call endPoint person/all");
        List<Person> personList = this.personService.getAllPersons();
        List<PersonDTO> personDTOList = personList.stream()
                    .map(PersonDTO::fromPerson)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTOList);
    }

    /**
     * Endpoint that returns the information of a person in the database.
     * @param firstName
     * @param lastName
     * @return a PersonDTO entity with ResponseEntity.
     */
    @GetMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<PersonDTO> getOnePerson(@PathVariable ("firstName") String firstName,
                                  @PathVariable ("lastName") String lastName)  {
        LOGGER.info("call endPoint GET /person/");
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

    /**
     *Endpoint which allows to save in database a person.
     * @param personDTO
     * @return a message of success.
     */
    @PostMapping("/person")
    public ResponseEntity<String> registerNewPerson(@RequestBody PersonDTO personDTO) {
        LOGGER.info("call endPoint POST/person");
        personService.addPerson(personDTO);
        LOGGER.info("call endPoint POST /person");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(personDTO.getFirstName() + " " + personDTO.getFirstName() + " was created in database.");
    }

    /**
     *Endpoint that allows to save in Database the modifications made to a person.
     * @param firstName
     * @param lastName
     * @param personDTO
     * @return a success message.
     */
    @PutMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> updatePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName,
                             @RequestBody PersonDTO personDTO ) {
        LOGGER.info("call endPoint PUT /person");

        personService.updatePerson(firstName, lastName, personDTO);

        return ResponseEntity.accepted().body(firstName + " " + lastName + "'s have been saved in the database.");
    }

    /**
     * endpoint that deletes a person in the DataBase
     * @param firstName
     * @param lastName
     * @return a success message.
     */
    @DeleteMapping(path = "/person/{firstName}/{lastName}")
    public ResponseEntity<String> deletePerson(@PathVariable("firstName") String firstName,
                             @PathVariable("lastName") String lastName) {
        personService.deletePerson(firstName, lastName);
        LOGGER.info("call endPoint DEL /person");
        return ResponseEntity.accepted().body(firstName + " " + lastName + " has been deleted from the database.");
    }
}

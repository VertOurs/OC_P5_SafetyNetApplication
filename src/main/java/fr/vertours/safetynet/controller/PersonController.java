package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.*;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.MedicalRecordService;
import fr.vertours.safetynet.service.PersonService;
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

    private final PersonService personService;


    public PersonController(PersonService personService, MedicalRecordController medicalRecordController) {
        this.personService = personService;
    }

    @GetMapping("/person/all")
    public ResponseEntity<List<PersonDTO>> getListOfPersons() {
        List<Person> personList = this.personService.getAllPersons();
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

    /* ****************************************** ENDPOINT 1 ****************************************************************** */                      // OK
    @GetMapping("/firestation")
    public FireStationInfoDTO getPersonFromFireStationWithCount(@RequestParam ("stationNumber") int station) {
        List<Person> personList = personService.findByStation(station);
        List<PersonForFireInfoDTO> personInfoList = personService.personFromFireStation(personList);
        return personService.getFireStationInfoDTOFromList(personInfoList,personList);
    }

    /* ****************************************** ENDPOINT 2 ****************************************************************** */                      // Ok

    @GetMapping("/childAlert")
    public ChildAlertDTO getListOfChildrenAtThisAddress(@RequestParam ("address") String address) {
        return personService.getChildrenAtThisAdress(address);
    }

    /* *****************************************ENDPOINT 3 ****************************************************************** */                        // OK
    @GetMapping("/phoneAlert")
    public List<String> getListPhoneNumberByFireStation(@RequestParam ("firestation") int station) {
        List<Person> personList = this.personService.findByStation(station);
        return  personList.stream().map(Person::getPhone).collect(Collectors.toList());
    }

    /* *****************************************  ENDPOINT 4  ************************************************************* */                          // OK
    @GetMapping("/fire")
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(@RequestParam("address") String address) {
        return this.personService.getListOfPersonForOneAddressWithFireStation(address);

    }

    /*  ***************************************  ENDPOINT 5  ************************************************************ */                        //a crée
    @GetMapping("/flood/stations")
    public List<?> endPoint5Flood(@RequestParam("stations") List<Integer> stationList) {
        System.out.println(stationList);
        return personService.getFloodByListOfStation(stationList);

    }


    /* ****************************************  ENDPOINT 6  ************************************************************* */                           //OK
    @GetMapping("/personInfo")
    public PersonInfoDTO getNameAddressAgeMailMedicationsAndAllergies(@RequestParam("firstName") String firstName, @RequestParam("lastName") String LastName) {
        Person person = this.personService.find(firstName, LastName);
        MedicalRecord medicalRecord = new MedicalRecord(person);
        PersonInfoDTO personInfoDTO =  new PersonInfoDTO(person, medicalRecord);
        return personInfoDTO;
    }


/* *************************************************PERSO ************************************************************************ */
    @GetMapping("/arthur")
    public AllInfoPersonDTO getAllInfoPerson(@RequestParam("firstName") String firstName, @RequestParam("lastName") String LastName) {

        return personService.createAllInfoPersonDTO(firstName, LastName);
    }





}

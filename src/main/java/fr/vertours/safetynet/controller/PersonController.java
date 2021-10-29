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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    private MedicalRecordService medicalRecordService;


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
    /* ****************************************  ENDPOINT 7 = Armel  ************************************************************ */
    @GetMapping("/communityEmail/{city}")
    public List<EmailDTO> getAllEmailOneCity(@PathVariable("city") String city) {
        List<Person> personList = this.personService.findByCity(city);
        List<EmailDTO> emailDTO = personList.stream().map(EmailDTO::fromPerson).collect(Collectors.toList());
        return emailDTO;
    }
    /* ****************************************  ENDPOINT 7  ************************************************************ */
    @GetMapping("/communityEmail")
    public List<EmailDTO> getAllEmailForOneCity(@RequestParam("city") String city) {
        List<Person> personList = this.personService.findByCity(city);
        List<EmailDTO> emailDTO = personList.stream().map(EmailDTO::fromPerson).collect(Collectors.toList());
        return emailDTO;
    }
    /* ****************************************  ENDPOINT 6  ************************************************************* */
    @GetMapping("/personInfo")
    public PersonInfoDTO getNameAddressAgeMailMedicationsAndAllergies(@RequestParam("firstName") String firstName, @RequestParam("lastName") String LastName) {
        Person person = this.personService.find(firstName, LastName);
        MedicalRecord medicalRecord = new MedicalRecord(person);
        PersonInfoDTO personInfoDTO =  new PersonInfoDTO(person, medicalRecord);
        return personInfoDTO;
    }
    /* *****************************************  ENDPOINT 4  ************************************************************* */
    @GetMapping("/fire")
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(@RequestParam("address") String address) {

        return this.medicalRecordService.getFireURL(address);
    }
    /* *****************************************ENDPOINT 3 ****************************************************************** */
    @GetMapping("/phoneAlert")
    public List<String> getListPhoneNumberByFireStation(@RequestParam ("firestation") int station) {
        List<Person> personList = personService.findByStation(station);
        List<String> stringList =  new ArrayList<>();
        for(Person p : personList) {
            stringList.add(p.getPhone());
        }
        return stringList;
    }
    /* ****************************************** ENDPOINT 2 ****************************************************************** */
/*
    @GetMapping("/childAlert")
    public List<String> getListOfChildrenAtThisAddress(@RequestParam ("address") String address) {
        List<Person> allPersonList = personService.findByAddress(address);
        List<String> childrenList = allPersonList.stream().filter(x -> {})
    }*/

    /* ****************************************** ENDPOINT 1 ****************************************************************** */
    @GetMapping("/firestation")
    public FireStationInfoDTO getPersonFromFireStationWithCount(@RequestParam ("stationNumber") int station) {
        List<Person> personList = personService.findByStation(station);
        List<PersonForFireInfoDTO> personInfoList = new ArrayList<>();
        FireStationInfoDTO fireStationInfoDTO = new FireStationInfoDTO();
        for(Person p : personList) {
            PersonForFireInfoDTO personFireInfo = new PersonForFireInfoDTO(p.getFirstName(), p.getLastName(),p.getAddress().getAddressName(), p.getPhone());
            MedicalRecord medicalRecord = medicalRecordService.find(p.getFirstName(), p.getLastName());
            LocalDate now = LocalDate.now();
            Period period = Period.between(now, medicalRecord.getBirthDate());
            int age = Math.abs(period.getYears());
            if(age >= 18) {
                fireStationInfoDTO.setNbAdultes(fireStationInfoDTO.getNbAdultes()+1);
            } else {
                fireStationInfoDTO.setNbEnfants(fireStationInfoDTO.getNbEnfants()+1);
            }
            personInfoList.add(personFireInfo);
        }
        fireStationInfoDTO.setPersonList(personInfoList);
        return fireStationInfoDTO;
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
}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    @PostMapping("/person")
    public void create(@RequestBody Person person) {
        this.personRepository.save(person);
    }

    @GetMapping("/person")
    public List<Person> getListPerson(){
        return this.personRepository.findAll();
    }

    @DeleteMapping("/person")
    public void deletePerson(){
        this.personRepository.deleteAll();
    }

    @PutMapping("/person")
    public void putPerson(@PathVariable Person person){
        this.personRepository.updatePerson(person);
    }

}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    PersonDTO personDTO;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(){

        personRepository.save(personDTO.createPerson());
    }
}

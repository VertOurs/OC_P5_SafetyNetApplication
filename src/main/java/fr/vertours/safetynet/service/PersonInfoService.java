package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoService implements IPersonInfoService {

    @Autowired
    PersonService personService;

    public Person find(String firstName, String lastName) {
        return personService.find(firstName, lastName);
    }
}

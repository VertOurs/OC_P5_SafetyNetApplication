package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoService  {



    @Autowired
    PersonService personService;

    public PersonInfoService(PersonService personService) {
        this.personService = personService;
    }

    public PersonInfoService() {
    }

    /**
     * find a person in Database.
     * @param firstName
     * @param lastName
     * @return a person entity.
     */

    public Person find(String firstName, String lastName) {

        return personService.find(firstName, lastName);
    }
}

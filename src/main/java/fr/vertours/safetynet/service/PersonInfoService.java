package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoService implements IPersonInfoService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonInfoService.class);

    @Autowired
    PersonService personService;

    /**
     * find a person in Database.
     * @param firstName
     * @param lastName
     * @return a person entity.
     */
    @Override
    public Person find(String firstName, String lastName) {
        LOGGER.info("call find method");
        return personService.find(firstName, lastName);
    }
}

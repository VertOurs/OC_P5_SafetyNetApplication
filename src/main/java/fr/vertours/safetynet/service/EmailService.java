package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {



    @Autowired
    PersonService personService;

    /**
     * find list of person by city.
     * @param city
     * @return a list of Person entity.
     */
    @Override
    public List<Person> findByCity(String city) {

        return personService.findByCity(city);
    }
}

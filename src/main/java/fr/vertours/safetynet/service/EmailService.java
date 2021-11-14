package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    PersonService personService;

    @Override
    public List<Person> findByCity(String city) {
        return personService.findByCity(city);
    }
}

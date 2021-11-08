package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailService implements IEmailService {

    @Autowired
    PersonService personService;

    @Override
    public List<Person> findByCity(String city) {
        return personService.findByCity(city);
    }
}

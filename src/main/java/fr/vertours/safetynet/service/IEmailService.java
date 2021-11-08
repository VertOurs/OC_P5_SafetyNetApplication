package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IEmailService {

    List<Person> findByCity(String city);
}

package fr.vertours.safetynet.configuration;

import com.jsoniter.JsonIterator;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class DataLoaderPerson {

    private PersonRepository personRepository;
    private DataBaseConfig dataBaseConfig;

    // alors je suis vraiemetn pas sur de moi la dessus, je suis meme pas sur de ce que je fait et pourquoi j'ai besoin d'appellé ma methode dans mon constructeur.
    public DataLoaderPerson(PersonRepository personRepository) {
        this.personRepository = personRepository;
        LoadPerson();
    }

    private void LoadPerson() {
        //personRepository.save(new Person());
    }
}

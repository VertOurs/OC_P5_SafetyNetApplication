package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);
    Person updatePerson(Person person);
}

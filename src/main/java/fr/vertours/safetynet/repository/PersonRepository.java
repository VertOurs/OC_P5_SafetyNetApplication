package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String firstName);
    Person findOneByFirstNameAndLastName(String firstName, String lastName);
    Person findOneById(Long ID);
    Person deleteByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddressIn();


}

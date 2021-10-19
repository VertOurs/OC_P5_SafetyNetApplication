package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);
    Person findOneByFirstNameAndLastName(String firstName, String lastName);
    Person findOneById(Long ID);
//    Person deleteOneByFirstNameandLastName(String firstName, String lastName);
    Person deletePersonByFirstNameAndLastName(String firstName, String lastName);
    Person deleteByFirstNameAndLastName(String firstName, String lastName);

}

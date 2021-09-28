package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String name);
    Person saveAll();
}

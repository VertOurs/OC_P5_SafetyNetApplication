package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergyRepository extends JpaRepository<Person, Long> {
}

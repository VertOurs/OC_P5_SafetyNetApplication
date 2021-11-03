package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AllergyRepository extends JpaRepository<Allergy, Long> {

    Allergy findOneByAllergy(String allergyName);
}

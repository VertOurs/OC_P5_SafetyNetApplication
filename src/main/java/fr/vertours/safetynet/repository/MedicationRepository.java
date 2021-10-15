package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {

    Medication findOneByMedication(String medication);
}

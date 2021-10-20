package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {


    MedicalRecord findOneByPerson_FirstNameAndPerson_LastName(String firstName, String lastName);
    Long findByPerson_FirstNameAndPerson_LastName(String firstName, String lastName);
}

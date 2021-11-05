package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface FireStationRepository extends JpaRepository<FireStation, Long> {

    FireStation findByStation (int station);
    Collection<FireStation> findFireStationByAddress(Address address);
    List<FireStation> findByStationIn(List<Integer> station);
}

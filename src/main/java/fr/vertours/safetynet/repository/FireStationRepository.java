package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.FireStation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireStationRepository extends JpaRepository<FireStation, Long> {

    FireStation findByStation (int station);
    FireStation updateStation (int station);
}

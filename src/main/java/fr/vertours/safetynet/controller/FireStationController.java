package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.repository.FireStationRepository;
import fr.vertours.safetynet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationController {

    private FireStationService fireStationService;

    public FireStationService getFireStationService() {
        return fireStationService;
    }

    public void setFireStationService(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }
}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.dto.FireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService implements IFireService {

    private final static Logger LOGGER = LoggerFactory.getLogger(FireService.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    @Override
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(
            String address) {
        return medicalRecordService.getFireURL(address);
    }
}

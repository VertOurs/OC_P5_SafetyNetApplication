package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService implements IFireService {
    @Autowired
    MedicalRecordService medicalRecordService;

    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(String address) {
        return medicalRecordService.getFireURL(address);
    }
}

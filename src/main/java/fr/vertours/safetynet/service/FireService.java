package fr.vertours.safetynet.service;


import fr.vertours.safetynet.dto.FireDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService implements IFireService {



    @Autowired
    MedicalRecordService medicalRecordService;

    /**
     * convert à String in List of FireDTO.
     * @param address
     * @return A list of FireDTO.
     */
    @Override
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(
            String address) {

        return medicalRecordService.getFireURL(address);
    }
}

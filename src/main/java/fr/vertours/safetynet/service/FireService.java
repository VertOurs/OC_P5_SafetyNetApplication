package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FireService implements IFireService {

    @Autowired
    MedicalRecordService medicalRecordService;

    public FireService(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    /**
     * convert à String in List of FireDTO.
     * @param address
     * @return A list of FireDTO.
     */
    @Override
    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(String address) {

        List<MedicalRecord> medicalRecordList = medicalRecordService.findMedicalRecordListByAddress(address);

        List<FireDTO> fireDTOList = new ArrayList<>();
        for(MedicalRecord medicalRecord : medicalRecordList) {
            FireDTO fireDTO = FireDTO.createFireDTOFromMedicalRecord(medicalRecord);
            fireDTOList.add(fireDTO);
        }
        return fireDTOList;
    }
}

package fr.vertours.safetynet.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DataLoaderMedicalRecord {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataLoaderMedicalRecord.class);

    @Autowired
    MedicalRecordService medicalRecordService;

    public void saveMedicalRecordInDB (Map<String, Object> map, ObjectMapper objectMapper) {
        List<Object> listOfMedicalRecordDTO =
                (List<Object>) map.get("medicalrecords");
        for(Object medicalRecord : listOfMedicalRecordDTO) {
            MedicalRecordDTO medicalRecordDTO =
                    objectMapper.convertValue(medicalRecord,
                            MedicalRecordDTO.class);
            medicalRecordService.save(medicalRecordDTO);
        }
    }
}

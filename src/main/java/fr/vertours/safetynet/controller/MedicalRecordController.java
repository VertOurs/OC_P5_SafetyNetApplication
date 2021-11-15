package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.service.MedicalRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.EndDocument;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MedicalRecordController {

    private final static Logger LOGGER = LoggerFactory.getLogger(MedicalRecordController.class);


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    /**
     *Endpoint that allows you to retrieve all MedicalRecord.
     * @return a list of MedicalRecordDTO entity with ResponseEntity.
     */
    @GetMapping("/medicalRecord/all")
    public ResponseEntity<List<MedicalRecordDTO>> getListOfMedicalRecord() {
        LOGGER.info("call endpoint : /medicalRecord/all");
        List <MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecord();
        List <MedicalRecordDTO> medicalRecordDTOList = medicalRecordList.stream().map(MedicalRecordDTO::fromMedicalRecord).collect(Collectors.toList());
        return ResponseEntity.accepted().body(medicalRecordDTOList);
    }

    /**
     *endpoint that allows access to the MedicalRecord of a person.
     * @param firstName
     * @param lastName
     * @return a success message with ResponseEntity..
     */
    @GetMapping(path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecordDTO> getOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        LOGGER.info("endpoint : GET /medicalRecord/firstName/LastName");
        MedicalRecord medicalRecord = medicalRecordService.getOneMedicalRecordByFirstAndLastName(firstName, lastName);
        return ResponseEntity.accepted().body(MedicalRecordDTO.fromMedicalRecord(medicalRecord));
    }

    /**
     *Endpoint which allows to save in database a MedicalRecord.
     * @param medicalRecordDTO
     * @return a success message with ResponseEntity..
     */
    @PostMapping("/medicalRecord")
    public ResponseEntity<String> registerNewMedicalPerson(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        LOGGER.info("endpoint : POST /medicalRecord");
        medicalRecordService.save(medicalRecordDTO);
        return ResponseEntity.accepted().body("the medicalRecord has been saved in the database");
    }

    /**
     * Endpoint that allows you to modify an existing MedicalRecord.
     * @param firstName
     * @param lastName
     * @param medicalRecordDTO
     * @return a success message with ResponseEntity.
     */
    @PutMapping (path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<String> updateMedicalRecord(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName,
                                    @RequestBody MedicalRecordDTO medicalRecordDTO) {
        LOGGER.info("Endpoint : PUT /medicalRecord/firstName/LastName");
        medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
        return ResponseEntity.accepted().body("The MedicalRecord modification has been saved to the database.");
    }

    /**
     * Endpoint that allows you to delete a MedicalRecord
     * @param firstName
     * @param lastName
     * @return a success message with ResponseEntity.
     */
    @DeleteMapping(path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<String> deleteOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        LOGGER.info("Endpoint : DEL /medicalRecord/firstName/LastName");
        medicalRecordService.deleteOneMedicalRecord(firstName, lastName);
        return  ResponseEntity.accepted().body("the MedicalRecord has been deleted from the database.");
    }



}

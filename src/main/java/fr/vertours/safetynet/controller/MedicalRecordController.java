package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MedicalRecordController {

    private final static Logger LOGGER = LogManager.getLogger(MedicalRecordController.class);


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping("/medicalRecord/all")
    public ResponseEntity<List<MedicalRecordDTO>> getListOfMedicalRecord() {
        List <MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecord();
        List <MedicalRecordDTO> medicalRecordDTOList = medicalRecordList.stream().map(MedicalRecordDTO::fromMedicalRecord).collect(Collectors.toList());
        return ResponseEntity.accepted().body(medicalRecordDTOList);
    }

    @GetMapping(path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<MedicalRecordDTO> getOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        MedicalRecord medicalRecord = medicalRecordService.getOneMedicalRecordByFirstAndLastName(firstName, lastName);
        return ResponseEntity.accepted().body(MedicalRecordDTO.fromMedicalRecord(medicalRecord));
    }
    @PostMapping("/medicalRecord")
    public ResponseEntity<String> registerNewMedicalPerson(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.save(medicalRecordDTO);
        return ResponseEntity.accepted().body("Le dossier médical à bien été créé.");
    }

    @PutMapping (path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<String> updateMedicalRecord(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName,
                                    @RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecordDTO);
        return ResponseEntity.accepted().body("Le dossier médical à bien été modifié.");
    }

    @DeleteMapping(path = "/medicalRecord/{firstName}/{lastName}")
    public ResponseEntity<String> deleteOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        medicalRecordService.deleteOneMedicalRecord(firstName, lastName);
        return  ResponseEntity.accepted().body("Le dossier médical à bien été supprimé.");
    }



}

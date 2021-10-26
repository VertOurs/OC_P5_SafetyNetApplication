package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping
    public List<MedicalRecordDTO> getListOfMedicalRecord() {
        List <MedicalRecord> medicalRecordList = medicalRecordService.getAllMedicalRecord();
        List <MedicalRecordDTO> medicalRecordDTOList = medicalRecordList.stream().map(MedicalRecordDTO::fromMedicalRecord).collect(Collectors.toList());
        return medicalRecordDTOList;
    }

    @GetMapping(path = "{firstName}/{lastName}")
    public MedicalRecordDTO getOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        MedicalRecord medicalRecord = medicalRecordService.getOneMedicalRecordByFirstAndLastName(firstName, lastName);
        return MedicalRecordDTO.fromMedicalRecord(medicalRecord);
    }
    @PostMapping
    public void registerNewMedicalPerson(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.save(medicalRecordDTO);
    }

    @PutMapping (path = "{firstName}/{lastName}")
    public void updateMedicalRecord(@PathVariable("firstName") String firstName,
                                    @PathVariable("lastName") String lastName,
                                    @RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.updateMedicalRecord(firstName, lastName, medicalRecordDTO);

    }

    @DeleteMapping(path = "{firstName}/{lastName}")
    public void deleteOneMedicalRecord(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
        medicalRecordService.deleteOneMedicalRecord(firstName, lastName);
    }



}

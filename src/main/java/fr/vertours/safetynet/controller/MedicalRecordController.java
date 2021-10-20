package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/medicalRecord")
public class MedicalRecordController {


    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }


    @GetMapping
    public List<MedicalRecord> getListOfPersons() {
        return medicalRecordService.getAllMedicalRecord();
    }
    // fonctionne mais me retourne toutes les infos de john boyd dupliqué
    @GetMapping(path = "{lastName}/{firstName}")
    public MedicalRecord getOneMedicalRecord(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        return medicalRecordService.getOneMedicalRecordByLastAndFirstName(lastName, firstName) ;
    }
    //visiblement probleme de format avec la date
    @PostMapping
    public void registerNewMedicalPerson(@RequestBody MedicalRecordDTO medicalRecordDTO) {
        medicalRecordService.addMedicalRecord(medicalRecordDTO);
    }
    //ne fonctionne pas
    @PutMapping (path = "{lastName}/{firstName}")
    public void updateMedicalRecord(@PathVariable("lastName") String lastName,
                                    @PathVariable("firstName") String firstName,
                                    @RequestParam(required = false) String birthdate,
                                    @RequestParam(required = false) Set<String> medication,
                                    @RequestParam(required = false) Set<String> Allergy) {
        medicalRecordService.updateMedicalRecord(lastName, firstName, birthdate,medication,Allergy);
    }
    // Id ne doit pas etre null
    @DeleteMapping(path = "{lastName}/{firstName}")
    public void deleteOneMedicalRecord(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) {
        medicalRecordService.deleteOneMedicalRecord(firstName, lastName);
    }



}

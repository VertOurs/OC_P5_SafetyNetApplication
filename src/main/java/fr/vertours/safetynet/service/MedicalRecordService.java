package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @PostMapping("/medicalRecord")
    public void create(@RequestBody MedicalRecord medicalRecord) {
        this.medicalRecordRepository.save(medicalRecord);
    }

    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getListMedicalRecord(){
        return this.medicalRecordRepository.findAll();
    }

    @DeleteMapping ("/medicalRecord")
    public void deleteMedicalRecord(){
        this.medicalRecordRepository.deleteAll();
    }

    @PutMapping ("/medicalRecord")
    public void putMedicalRecord(@PathVariable Person person){
        this.medicalRecordRepository.updateMedicalRecord(person);

    }
}

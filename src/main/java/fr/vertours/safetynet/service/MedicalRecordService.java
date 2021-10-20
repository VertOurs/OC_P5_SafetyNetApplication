package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private AllergyService allergyService;




    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAll();
    }
    public MedicalRecord getOneMedicalRecordByLastAndFirstName(String lastName, String firstName) {
        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(lastName, firstName);

    }

    public MedicalRecord save(MedicalRecordDTO medicalRecord) {
        Person person = personService.find(medicalRecord.getFirstName(), medicalRecord.getLastName());
        LocalDate birthDate = LocalDate.parse(medicalRecord.getBirthdate(), DATE_TIME_FORMATTER);
        Set<String> medicationSet = medicalRecord.getMedications();
        Set<String> allergySet = medicalRecord.getAllergies();
        Set<Medication> setObjectMedication = makeMedication(medicationSet);
        Set<Allergy> setObjectAllergy = makeAllergy(allergySet);

        MedicalRecord medicalRecord1 = new MedicalRecord();
        medicalRecord1.setPerson(person);
        medicalRecord1.setBirthDate(birthDate);
        medicalRecord1.setAllergies(setObjectAllergy);
        medicalRecord1.setMedications(setObjectMedication);

        return medicalRecordRepository.save(medicalRecord1);

    }

    private Set<Medication> makeMedication(Set<String> medicationName) {
        Set<Medication> setMedication = new HashSet<>();
        for(String s : medicationName) {
            Medication medication = medicationService.find(s);
            if(medication == null) {
                medication = medicationService.save(s);
            }
            setMedication.add(medication);
        }
        return setMedication;
    }

    private Set<Allergy> makeAllergy(Set<String> allergyName) {
        Set<Allergy> setAllergy = new HashSet<>();
        for(String s : allergyName){
            Allergy allergy = allergyService.find(s);
            if(allergy == null) {
                allergy = allergyService.save(s);
            }
            setAllergy.add(allergy);
        }
        return setAllergy;
    }

    public void addMedicalRecord(MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordDTO.createMedicalRecord();
        Person person = personService.find(medicalRecordDTO.getFirstName(), medicalRecordDTO.getLastName());
        if(person == null) {
            System.out.println("Merci de crée d'abord une personne avant de créé le dossier lui corespondant !!");
        } else {
            medicalRecord.setPerson(person);
            medicalRecordRepository.save(medicalRecord);
        }
    }

    public void updateMedicalRecord(String lastName, String firstName, String birthDate, Set<String> medication, Set<String> allergy) {
        MedicalRecord medicalRecord = medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localBirthDate = LocalDate.parse(birthDate, formatDate);


        if(birthDate != null && birthDate.length() >0 && !Objects.equals(medicalRecord.getBirthDate(), localBirthDate)) {
            medicalRecord.setBirthDate(localBirthDate);
        }
        if(medication != null && medication.size() > 0 && !Objects.equals(medicalRecord.getMedications(), medication)) {
            Set<Medication> medicationSet = null;
            for(String s : medication) {
                Medication medication1 = new Medication(s);
                medicationSet.add(medication1);
            }
            medicalRecord.setMedications(medicationSet);
        }
        if(allergy != null && allergy.size() > 0 && !Objects.equals(medicalRecord.getAllergies(),allergy)) {
            Set<Allergy> allergySet = null;
            for(String s : allergy) {
                Allergy allergy1 = new Allergy(s);
                allergySet.add(allergy1);
            }
            medicalRecord.setAllergies(allergySet);
        }
    }

    public void deleteOneMedicalRecord(String firstName, String lastName) {
        Long id = medicalRecordRepository.findByPerson_FirstNameAndPerson_LastName(firstName, lastName);
        medicalRecordRepository.deleteById(id);
    }
}


package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireDTO;
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
import java.util.*;
import java.util.stream.Collectors;

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

    public MedicalRecord find(String firstName, String lastName) {
        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);
    }
    public List<MedicalRecord> getAllMedicalRecord() {
        return medicalRecordRepository.findAll();
    }

    public List<MedicalRecord> getMedicalRecordByListOfPerson(List<Person> personList) {
        List<MedicalRecord> allMedicalRecordList = medicalRecordRepository.findAll();
        List<MedicalRecord>  medicalRecordList = new ArrayList<>();
        for(Person p : personList) {
            for(MedicalRecord mR : allMedicalRecordList) {
                if(p.equals(mR.getPerson())){
                    medicalRecordList.add(mR);
                }
            }
        }
        return medicalRecordList;
    }
    public MedicalRecord getOneMedicalRecordByFirstAndLastName(String firstName, String lastName) {
        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);

    }
    public List<FireDTO> getFireURL(String address) {
        List<MedicalRecord> medicalRecordList = this.medicalRecordRepository.findByPerson_Address_AddressName(address);
        List<FireDTO> fireDTOList = new ArrayList<>();
        for(MedicalRecord medicalRecord : medicalRecordList) {
            FireDTO fireDTO = new FireDTO();
            fireDTO.setFirstName(medicalRecord.getPerson().getFirstName());
            fireDTO.setLastName(medicalRecord.getPerson().getLastName());
            fireDTO.setPhone(medicalRecord.getPerson().getPhone());
            fireDTO.setAge(medicalRecord.getBirthDate().toString());
            fireDTO.setMedicationSet(medicalRecord.getMedications());
            fireDTO.setAllergySet((medicalRecord.getAllergies()));
            fireDTOList.add(fireDTO);
        }
        return fireDTOList;
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



    public void updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO) {
        MedicalRecord medicalRecord = medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);

        if(medicalRecordDTO.getBirthdate() != null ) {
            LocalDate localBirthDate = LocalDate.parse(medicalRecordDTO.getBirthdate(), DATE_TIME_FORMATTER);
            medicalRecord.setBirthDate(localBirthDate);
        }
        if(medicalRecordDTO.getMedications() != null ) {
            if (medicalRecordDTO.getMedications().size() > 0) {
                Set<Medication> medicationSet = medicalRecordDTO.getMedications().stream().map(medication -> medicationService.findOrCreate(medication)).collect(Collectors.toSet());
                medicalRecord.setMedications(medicationSet);
            } else {
                medicalRecord.removeAllMedications();
            }
        }
        if(medicalRecordDTO.getAllergies() != null ) {
            if (medicalRecordDTO.getAllergies().size() > 0 ){
            Set<Allergy> allergySet = medicalRecordDTO.getAllergies().stream().map(allergy -> allergyService.findOrCreate(allergy) ).collect(Collectors.toSet());
            medicalRecord.setAllergies(allergySet);
        } else {
            medicalRecord.removeAllAllergies();
            }
        }
        medicalRecordRepository.save(medicalRecord);
    }

    public void deleteOneMedicalRecord(String firstName, String lastName) {
        MedicalRecord medicalRecord = find(firstName, lastName);
        medicalRecordRepository.delete(medicalRecord);
    }
    public List<MedicalRecord> findByPersonList(List<Person> personList) {
        return medicalRecordRepository.findByPersonIn(personList);
    }
    public MedicalRecord findMedicalRecordInListOfMR(String firstName, String lastName, List<MedicalRecord> medicalRecordList) {
        MedicalRecord medicalRecord = new MedicalRecord();
        for (MedicalRecord mR : medicalRecordList) {
            if(firstName.equals(mR.getPerson().getFirstName()) && lastName.equals(mR.getPerson().getLastName())){
                medicalRecord = mR;
            }
        }
        return medicalRecord;
    }
}


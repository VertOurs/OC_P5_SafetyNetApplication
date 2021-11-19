package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.dto.FireDTO;
import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.model.exceptions.EmptyDBException;
import fr.vertours.safetynet.model.exceptions.PersonNotFoundException;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MedicalRecordService  {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private final MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private AllergyService allergyService;


    public MedicalRecordService(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    /**
     * finds a MedicalRecord in the database.
     * @param firstName
     * @param lastName
     * @return MedicalRecord Entity.
     */
    public MedicalRecord find(String firstName, String lastName) {
        Optional<MedicalRecord> existingMedicalRecord = Optional.ofNullable(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName));
        if(existingMedicalRecord.isEmpty()) {
            throw new PersonNotFoundException(firstName, lastName);
        }
        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);
    }

    /**
     * gives a list of all MedicalRecords present in the Database
     * @return a list of MedicalRecord entity.
     */
    public List<MedicalRecord> getAllMedicalRecord() {

        List<MedicalRecord> medicalRecordList = medicalRecordRepository.findAll();
        if(medicalRecordList.isEmpty()) {
            throw new EmptyDBException();
        }
        return medicalRecordList;
    }

    /**
     * find the list of MedicalRecord with a list of person.
     * @param personList
     * @return a list of MedicalRecord.
     */
    public List<MedicalRecord> getMedicalRecordByListOfPerson(List<Person> personList) {

        List<MedicalRecord> allMedicalRecordList = getAllMedicalRecord();
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

    /**
     *allows to have access to a Medical Record present in the database.
     * @param firstName
     * @param lastName
     * @return a MedicalRecord entity.
     */
    public MedicalRecord getOneMedicalRecordByFirstAndLastName(String firstName, String lastName) {

        return medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName);

    }

    public List<MedicalRecord> findMedicalRecordListByAddress(String address) {
        return medicalRecordRepository.findByPerson_Address_AddressName(address);
    }


    /**
     *save a medicalRecord to a person.
     * @param medicalRecord
     * @return MedicalRecord
     */
    public MedicalRecord save(MedicalRecordDTO medicalRecord) {

        Optional<Person> existingPerson = Optional.ofNullable(personService.find(medicalRecord.getFirstName(), medicalRecord.getLastName()));
        if(existingPerson.isEmpty()) {
            throw new PersonNotFoundException(medicalRecord.getFirstName(), medicalRecord.getLastName());
        }
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

    /**
     * creates a Set<Medication> by using the database.
     * @param medicationName
     * @return a set of Medication entity.
     */
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

    /**
     * creates a Set<Allergy> by using the database.
     * @param allergyName
     * @return a set of Allergy entity.
     */
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


    /**
     * modifies an existing MedicalRecord.
     * @param firstName
     * @param lastName
     * @param medicalRecordDTO
     */
    public void updateMedicalRecord(String firstName, String lastName, MedicalRecordDTO medicalRecordDTO) {

        Optional<MedicalRecord> existingMedicalRecord = Optional.ofNullable(medicalRecordRepository.findOneByPerson_FirstNameAndPerson_LastName(firstName, lastName));
        if(existingMedicalRecord.isEmpty()) {
            throw new PersonNotFoundException(firstName, lastName);
        }
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

    /**
     *Delete a MedicalRecord from the database.
     * @param firstName
     * @param lastName
     */
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


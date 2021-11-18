package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.util.CustomTools;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FloodContactDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String age;
    private Set<Medication> medications;
    private Set<Allergy> allergies;

    public FloodContactDTO(String firstName,
                           String lastName,
                           String phoneNumber,
                           String age,
                           Set<Medication> medications,
                           Set<Allergy> allergies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.medications = medications;
        this.allergies = allergies;
    }

    public FloodContactDTO() {
    }

    public static FloodContactDTO fromPersonandMedicalRecord(
            Person person, MedicalRecord medicalRecord) {
        FloodContactDTO contactDTO = new FloodContactDTO();
        contactDTO.setFirstName(person.getFirstName());
        contactDTO.setLastName(person.getLastName());
        contactDTO.setPhoneNumber(person.getPhone());
        contactDTO.setAge(String.valueOf(
                CustomTools.calculateAgewithLocalDate(
                        medicalRecord.getBirthDate())));
        contactDTO.setMedications(medicalRecord.getMedications());
        contactDTO.setAllergies(medicalRecord.getAllergies());
        return contactDTO;
    }
    public static List<FloodContactDTO> fromListPersonMr(
            List<Person> personList,
            List<MedicalRecord> medicalRecordList) {
        List<FloodContactDTO> floodContactDTOList = new ArrayList<>();
        for (Person p : personList) {
            MedicalRecord medicalRecord =  medicalRecordList.stream()
                    .filter( mr -> mr.getPerson().equals(p)).findFirst().get();
            FloodContactDTO floodDTO =
                    fromPersonandMedicalRecord(p,medicalRecord);
            floodContactDTOList.add(floodDTO);
        }
        return floodContactDTOList;
    }


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public Set<Medication> getMedications() {
        return medications;
    }
    public void setMedications(Set<Medication> medications) {
        this.medications = medications;
    }

    public Set<Allergy> getAllergies() {
        return allergies;
    }
    public void setAllergies(Set<Allergy> allergies) {
        this.allergies = allergies;
    }
}

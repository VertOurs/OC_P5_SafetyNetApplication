package fr.vertours.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.model.Person;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PersonInfoDTO {

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private String firstName;
    private String lastName;
    private String address;
    private LocalDate birthDate;
    private String email;
    private Set<String> Medications;
    private Set<String> Allergies;

    public PersonInfoDTO() {
    }

    public PersonInfoDTO(Person person, MedicalRecord medicalRecord) {
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.address = person.getAddress().getAddressName();
        this.birthDate = medicalRecord.getBirthDate();
        this.email = person.getEmail();

        Medications = medicationToString(medicalRecord.getMedications());
        Allergies = allergyToString(medicalRecord.getAllergies());
    }

    public Set<String> medicationToString(Set<Medication> medicationSet) {
        Set<String> stringSet = new HashSet<>();
        for(Medication m : medicationSet) {
            stringSet.add(m.getMedication());
        }
        return stringSet;
    }
    public Set<String> allergyToString(Set<Allergy> allergySet) {
        Set<String> stringSet= new HashSet<>();
        for(Allergy a : allergySet) {
            stringSet.add(a.getAllergy());
        }
        return stringSet;
    }


    public static PersonInfoDTO fromPersonAndMedicalRecord(Person person, MedicalRecord medicalRecord) {
        PersonInfoDTO personInfoDTO = new PersonInfoDTO();
        personInfoDTO.setAddress(person.getAddress().getAddressName());
        personInfoDTO.setBirthDate(medicalRecord.getBirthDate());
        personInfoDTO.setEmail(person.getEmail());
        Set<Medication> medicationSet = medicalRecord.getMedications();
        Set<String> stringMedicationSet = new HashSet<>();
        for(Medication m : medicationSet) {
            stringMedicationSet.add(m.getMedication());
        }
        personInfoDTO.setMedications(stringMedicationSet);
        Set<Allergy> allergySet = medicalRecord.getAllergies();
        Set<String> stringAllergySet = new HashSet<>();
        for(Allergy a : allergySet) {
            stringAllergySet.add(a.getAllergy());
        }
        personInfoDTO.setAllergies((stringAllergySet));

        return personInfoDTO;
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

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Set<String> getMedications() {
        return Medications;
    }
    public void setMedications(Set<String> medications) {
        Medications = medications;
    }

    public Set<String> getAllergies() {
        return Allergies;
    }
    public void setAllergies(Set<String> allergies) {
        Allergies = allergies;
    }
}

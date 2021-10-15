package fr.vertours.safetynet.dto;

import java.util.HashSet;
import java.util.Set;

public class MedicalRecordDTO {
    //même variable que dans Person, quand dois-je mappé mes varaible a un objet person?
    private String firstName;
    private String lastName;
    //es ce que j'ai besoin d'avoir une coorespondance total? birthdate dans le Json etpasBirthDate
    //pertinent de mettre sa en Date?
    private String birthdate;
    private Set<String> medications = new HashSet<>();
    private Set<String> allergies = new HashSet<>();

    @Override
    public String toString() {
        return "MedicalRecordDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthdate + '\'' +
                ", medications='" + medications + '\'' +
                ", allergies='" + allergies + '\'' +
                '}';
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Set<String> getMedications() {
        return medications;
    }

    public void setMedications(Set<String> medications) {
        this.medications = medications;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(Set<String> allergies) {
        this.allergies = allergies;
    }
}

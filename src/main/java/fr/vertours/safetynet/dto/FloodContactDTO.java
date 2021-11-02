package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Medication;

import java.util.List;

public class FloodContactDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int age;
    private List<Medication> medications;
    private List<Allergy> allergies;


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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public List<Medication> getMedications() {
        return medications;
    }
    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }

    public List<Allergy> getAllergies() {
        return allergies;
    }
    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
}

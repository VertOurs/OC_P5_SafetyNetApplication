package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Medication;

import java.util.Set;

public class FireDTO {

    private String firstName;
    private String lastName;
    private String phone;
    private String age;
    private Set<Medication> medicationSet;
    private Set<Allergy> allergySet;

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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }
    public void setAge(String age) {
        this.age = age;
    }

    public Set<Medication> getMedicationSet() {
        return medicationSet;
    }
    public void setMedicationSet(Set<Medication> medicationSet) {
        this.medicationSet = medicationSet;
    }

    public Set<Allergy> getAllergySet() {
        return allergySet;
    }
    public void setAllergySet(Set<Allergy> allergySet) {
        this.allergySet = allergySet;
    }
}

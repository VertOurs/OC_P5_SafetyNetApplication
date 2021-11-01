package fr.vertours.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AllInfoPersonDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String zip;
    private String phone;
    private String email;
    private LocalDate birthdate;
    private Set<String> medications = new HashSet<>();
    private Set<String> allergies = new HashSet<>();
    private int station;




    public AllInfoPersonDTO(String firstName,
                            String lastName,
                            String address,
                            String city,
                            String zip,
                            String phone,
                            String email,
                            LocalDate birthdate,
                            Set<String> medications,
                            Set<String> allergies,
                            int station) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.medications = medications;
        this.allergies = allergies;
        this.station = station;
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

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
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

    public int getStation() {
        return station;
    }
    public void setStation(int station) {
        this.station = station;
    }
}

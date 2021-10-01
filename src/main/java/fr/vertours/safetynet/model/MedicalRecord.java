package fr.vertours.safetynet.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Person person;

    @Column (nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @ManyToMany
    @JoinTable (
            name = "MedicalRecord_Medication",
            joinColumns =  @JoinColumn (name = "MedicalRecord"),
            inverseJoinColumns = @JoinColumn (name = "Medication")
    )
    private Set<Medication> medications;

    @ManyToMany
    @JoinTable (
            name = "MedicalRecord_Allergy",
            joinColumns =  @JoinColumn (name = "MedicalRecord"),
            inverseJoinColumns = @JoinColumn (name = "Allergy")
    )
    private Set<Allergy> allergies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
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

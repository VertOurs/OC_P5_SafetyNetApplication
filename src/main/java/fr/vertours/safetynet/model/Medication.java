package fr.vertours.safetynet.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false, unique = true)
    private String medication;

    @ManyToMany (mappedBy = "medications")
    private Set<MedicalRecord> medicalRecord;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public Set<MedicalRecord> getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(Set<MedicalRecord> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}

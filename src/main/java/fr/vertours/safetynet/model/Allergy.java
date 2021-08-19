package fr.vertours.safetynet.model;

import java.util.List;

public class Allergy {
    private int id;
    private String allergy;
    private List<MedicalRecord> medicalRecord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public List<MedicalRecord> getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(List<MedicalRecord> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}

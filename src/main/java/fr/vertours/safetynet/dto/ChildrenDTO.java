package fr.vertours.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.vertours.safetynet.model.MedicalRecord;

import java.time.LocalDate;
import java.time.Period;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ChildrenDTO {

    private String firstName;

    private String lastName;

    private int age;



    public static ChildrenDTO ChildrenfromMedicalRecord(MedicalRecord medicalRecord) {
        ChildrenDTO menberFamilyDTO = new ChildrenDTO();
        menberFamilyDTO.setFirstName(medicalRecord.getPerson().getFirstName());
        menberFamilyDTO.setLastName(medicalRecord.getPerson().getLastName());
        menberFamilyDTO.setAge(menberFamilyDTO.calculateAgewithLocalDate(medicalRecord.getBirthDate()));
        return menberFamilyDTO;
    }
    private int calculateAgewithLocalDate (LocalDate date) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(now, date);
        return Math.abs(period.getYears());
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

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
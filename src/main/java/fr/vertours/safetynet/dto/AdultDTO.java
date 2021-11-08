package fr.vertours.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import fr.vertours.safetynet.model.MedicalRecord;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AdultDTO {

    private String firstName;
    private String lastName;


    public static AdultDTO AdultfromMedicalRecord(MedicalRecord medicalRecord) {
        AdultDTO menberFamilyDTO = new AdultDTO();
        menberFamilyDTO.setFirstName(medicalRecord.getPerson().getFirstName());
        menberFamilyDTO.setLastName( medicalRecord.getPerson().getLastName());
        return menberFamilyDTO;
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
}

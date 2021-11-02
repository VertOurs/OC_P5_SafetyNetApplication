package fr.vertours.safetynet.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ChildAlertDTO {

    private List<ChildrenDTO> enfants;

    private List<AdultDTO> autresMenbresDuFoyer;


    public List<ChildrenDTO> getEnfants() {
        return enfants;
    }
    public void setEnfants(List<ChildrenDTO> enfants) {
        this.enfants = enfants;
    }

    public List<AdultDTO> getAutresMenbresDuFoyer() {
        return autresMenbresDuFoyer;
    }
    public void setAutresMenbresDuFoyer(List<AdultDTO> autresMenbresDuFoyer) {
        this.autresMenbresDuFoyer = autresMenbresDuFoyer;
    }
}

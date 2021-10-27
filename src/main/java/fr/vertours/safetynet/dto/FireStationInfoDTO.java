package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Person;

import java.util.List;

public class FireStationInfoDTO {
    private List<PersonForFireInfoDTO> personList;
    private int nbEnfants = 0;
    private int nbAdultes = 0;

    public List<PersonForFireInfoDTO> getPersonList() {
        return personList;
    }

    public void setPersonList(List<PersonForFireInfoDTO> personList) {
        this.personList = personList;
    }

    public int getNbEnfants() {
        return nbEnfants;
    }

    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    public int getNbAdultes() {
        return nbAdultes;
    }

    public void setNbAdultes(int nbAdultes) {
        this.nbAdultes = nbAdultes;
    }
}

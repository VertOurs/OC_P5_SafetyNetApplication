package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Person;

public class EmailDTO {

    private String email;


    public static EmailDTO fromPerson (Person person) {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setEmail(person.getEmail());
        return emailDTO;
    }


    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }


}

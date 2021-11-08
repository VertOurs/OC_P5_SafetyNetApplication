package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Person;

public class PersonForFireInfoDTO {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    public PersonForFireInfoDTO() {
    }


    public static PersonForFireInfoDTO fromPerson(Person person) {
        PersonForFireInfoDTO personDTO = new PersonForFireInfoDTO();
        personDTO.setFirstName(person.getFirstName());
        personDTO.setLastName(person.getLastName());
        personDTO.setAddress(person.getAddress().getAddressName());
        personDTO.setPhone(person.getPhone());
        return personDTO;
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

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}

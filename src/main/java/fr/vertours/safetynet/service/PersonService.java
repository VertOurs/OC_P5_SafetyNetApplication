package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    PersonDTO personDTO;

    @Autowired
    AddressService addressService;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public void savePerson(){
        personRepository.save(personDTO.createPerson());
    }

    public void saveAll(Collection<Person> collection) {
        personRepository.saveAll(collection);
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    public Person getOnePersonByID(Long personID) {
        return personRepository.findOneById(personID);
    }
    public void addPerson(PersonDTO personDTO) {
        Person person = personDTO.createPerson();
        Address address = addressService.find(personDTO.getAddress());
        if(address == null) {
            address = addressService.save(personDTO.getAddress());
        }
        person.setAddress(address);
        personRepository.save(person);
    }
    public void deletePerson(String firstName, String lastName) {
        personRepository.deleteByFirstNameAndLastName(firstName, lastName);


    }

    public Person find(String firstName, String lastName) {
        return personRepository.findOneByFirstNameAndLastName(firstName,lastName);
    }

    @Transactional // sa fait quoi ? et pourquoi je peux pas surcharger mes methodes avec cette annotation
    public void updatePerson(String firstName, String lastName, String address, String city, String zip, String phone, String email) {
        Person person = personRepository.findOneByFirstNameAndLastName(firstName, lastName);
        Address addressObject = addressService.save(address);
        if(address != null && address.length() > 0 && !Objects.equals(person.getAddress(), address)) {
            person.setAddress(addressObject);
        }
        if(city != null && city.length() >0 && !Objects.equals(person.getCity(), city)) {
            person.setCity(city);
        }
        if(zip != null && zip.length() > 0 && !Objects.equals(person.getZip(), zip)) {
            person.setZip(zip);
        }
        if(phone != null && phone.length() > 0 && !Objects.equals(person.getPhone(), phone)) {
            person.setPhone(phone);
        }
        if(email != null && email.length() > 0 && !Objects.equals(person.getEmail(), email)) {
            person.setEmail(email);
        }
    }
}

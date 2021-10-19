package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
    public void addPerson(PersonDTO personDTO) {
        Person person = personDTO.createPerson();
        Address address = addressService.find(personDTO.getAddress());
        if(address == null) {
            address = addressService.save(personDTO.getAddress());
        }
        person.setAddress(address);
        personRepository.save(person);
    }
    public void deletePerson(Long personID) {
        boolean exists = personRepository.existsById(personID);
        if (!exists) {
            throw  new IllegalStateException("Person with this id : "+personID+", does not exists");
        }
        personRepository.deleteById(personID);
    }

    public Person find(String firstName, String lastName) {
        return personRepository.findOneByFirstNameAndLastName(firstName,lastName);
    }
}

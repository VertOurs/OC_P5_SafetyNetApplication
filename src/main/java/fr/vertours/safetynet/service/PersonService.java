package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.*;
import fr.vertours.safetynet.model.exceptions.EmptyDBException;
import fr.vertours.safetynet.model.exceptions.PersonAlreadyPresentException;
import fr.vertours.safetynet.model.exceptions.PersonNotFoundException;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static fr.vertours.safetynet.util.CustomTools.calculateAgewithLocalDate;

@Service
public class PersonService implements IEmailService, IPersonInfoService {



    private final PersonRepository personRepository;

    private final AddressService addressService;




    public PersonService(PersonRepository personRepository, AddressService addressService) {
        this.personRepository = personRepository;
        this.addressService = addressService;
    }


    /**
     *adds a person to the database.
     * @param personDTO
     */
    public void addPerson(PersonDTO personDTO) {
        Person person = personDTO.createPerson();
        Address address = addressService.findOrCreate(personDTO.getAddress());

        person.setAddress(address);
        Optional<Person> existingPerson = Optional.ofNullable(personRepository.findOneByFirstNameAndLastName(person.getFirstName(), person.getLastName()));
        if(existingPerson.isPresent()) {
            throw new PersonAlreadyPresentException(person.getFirstName(), person.getLastName());
        }
        personRepository.save(person);
    }

    public void saveAll(Collection<Person> collection) {
        personRepository.saveAll(collection);
    }

    /**
     * Retrieves all the people in the database
     * @return A list of <Person> Entities.
     */
    public List<Person> getAllPersons() {
       List<Person> personList = personRepository.findAll();
       if(personList.isEmpty()) {
           throw new EmptyDBException();
       }
       return personRepository.findAll();
    }

    /**
     *  deletes a person in the DataBase
     * @param firstName
     * @param lastName
     */
    public void deletePerson(String firstName, String lastName) {
        Person person = find(firstName, lastName);
        personRepository.delete(person);
    }

    /**
     * find a person in Database
     * @param firstName
     * @param lastName
     * @return a Person Entity
     */
    public Person find(String firstName, String lastName)  {

        Optional<Person> existingPerson = Optional.ofNullable(personRepository.findOneByFirstNameAndLastName(firstName, lastName));

        if(existingPerson.isEmpty()) {
            throw new PersonNotFoundException(firstName, lastName);
        }
        return personRepository.findOneByFirstNameAndLastName(firstName,lastName);
    }


    /**
     * Find list of person by city.
     * @param city
     * @return list of Person entity.
     */
    public List<Person> findByCity(String city) {
        List<Person> personList = personRepository.findAllByCity(city);
        if (personList.isEmpty()) {
            throw new EmptyDBException();
        }
        return personList;
    }

    /**
     * find list of person by address.
     * @param address
     * @return list of Person entity.
     */
    public List<Person> findByAddress(String address) {
        List<Person> personList = personRepository.findByAddress_AddressName(address);
        if (personList.isEmpty()) {
            throw new EmptyDBException();
        }
        return personList;
    }

    /**
     * Find List of person with collection of Address entity.
     * @param addresseCollections
     * @return A List of Person Entity.
     */
    public List<Person> findByAddressIn(Collection<Address> addresseCollections) {
        List<Person> personList = personRepository.findByAddressIn(addresseCollections);

        if(personList.isEmpty()) {
            throw new EmptyDBException();
        }
        return personList;
    }



    /**
     *allows you to modify the values of a person in the database
     * @param firstName
     * @param lastName
     * @param personDTO
     */
    public void updatePerson(String firstName, String lastName, PersonDTO personDTO) {
        Person person = Optional.ofNullable(personRepository.findOneByFirstNameAndLastName(firstName, lastName))
                .orElseThrow(()-> new PersonNotFoundException(firstName, lastName));

        if(personDTO.getAddress() != null) {
            Address address = addressService.findOrCreate(personDTO.getAddress());
            person.setAddress(address);
        }
        if (personDTO.getCity() != null) {
            person.setCity(personDTO.getCity());
        }
        if (personDTO.getZip() != null) {
            person.setZip(personDTO.getZip());
        }
        if (personDTO.getPhone() != null) {
            person.setPhone(personDTO.getPhone());
        }
        if (personDTO.getEmail() != null) {
            person.setEmail(personDTO.getEmail());
        }
        personRepository.save(person);
    }







    /**
     * find a list of personn by their address.
     * @param address
     * @return list of Person entity.
     */
    public List<Person> findListOfPersonByAddress(Address address) {
        List<Person> personList = personRepository.findByAddress_AddressName(address.getAddressName());
        if (personList.isEmpty()) {
            throw new EmptyDBException();
        }
        return personList;
    }


}

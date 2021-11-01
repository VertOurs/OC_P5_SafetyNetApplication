package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.AllInfoPersonDTO;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    PersonDTO personDTO;

    @Autowired
    AddressService addressService;
    @Autowired
    FireStationService fireStationService;
    @Autowired
    MedicalRecordService medicalRecordService;
    @Autowired
    MedicationService medicationService;
    @Autowired
    AllergyService allergyService;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
    public void saveAll(Collection<Person> collection) {
        personRepository.saveAll(collection);
    }
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void deletePerson(String firstName, String lastName) {
        Person person = find(firstName, lastName);
        personRepository.delete(person);
    }
    public Person find(String firstName, String lastName) {
        return personRepository.findOneByFirstNameAndLastName(firstName,lastName);
    }
    public List<Person> findByLastName(String lastname) {
        return personRepository.findByLastName(lastname);
    }
    public List<Person> findByCity(String city) {
        return personRepository.findAllByCity(city);
    }
    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress_AddressName(address);
    }
    public List<Person> findByStation(int station) {
        FireStation fireStation = fireStationService.findOneStation(station);
        Set<Address> addressSet = fireStation.getAddress();
        return personRepository.findByAddressIn(addressSet);
    }
    public void updatePerson(String firstName, String lastName, PersonDTO personDTO) {
        Person person = personRepository.findOneByFirstNameAndLastName(firstName, lastName);

        if(personDTO.getAddress() != null) {
            Address address = new Address(personDTO.getAddress());
            addressService.save(address);
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
    public AllInfoPersonDTO createAllInfoPersonDTO(String firstName, String lastName) {
        Person person = personRepository.findOneByFirstNameAndLastName(firstName, lastName);
        MedicalRecord medicalRecord = medicalRecordService.find(firstName, lastName);
        FireStation fireStation = fireStationService.findOneStationByAddress(person.getAddress());
        AllInfoPersonDTO MyDTO = new AllInfoPersonDTO(person.getFirstName(),
                person.getLastName(),
                person.getAddress().getAddressName(),
                person.getCity(),
                person.getZip(),
                person.getPhone(),
                person.getEmail(),
                medicalRecord.getBirthDate(),
                medicationService.makeStringSetFromMedication(medicalRecord.getMedications()),
                allergyService.makeStringSetFromAllergy(medicalRecord.getAllergies()),
                fireStation.getStation());
        return MyDTO;
    }


}

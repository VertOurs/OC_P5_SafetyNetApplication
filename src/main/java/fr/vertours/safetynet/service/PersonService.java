package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.*;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

import static fr.vertours.safetynet.util.CustomTools.calculateAgewithLocalDate;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    AddressService addressService;

    @Autowired
    FireStationService fireStationService;

    @Autowired
    MedicalRecordService medicalRecordService;



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

    public List<Person> findByCity(String city) {
        return personRepository.findAllByCity(city);
    }

    public List<Person> findByAddress(String address) {
        return personRepository.findByAddress_AddressName(address);
    }

    public List<Person> findByAddressIn(Collection<Address> addresseCollections) {
        return personRepository.findByAddressIn(addresseCollections);
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



    public List<PersonForFireInfoDTO> personFromFireStation(List<Person> personList) {
       List<PersonForFireInfoDTO> infoDTOS = personList.stream().map(PersonForFireInfoDTO::fromPerson).collect(Collectors.toList());
      return infoDTOS;
    }

    public FireStationInfoDTO getFireStationInfoDTOFromList (List<PersonForFireInfoDTO> personInfoList, List<Person> personList) {

        List<MedicalRecord> mRList = medicalRecordService.getMedicalRecordByListOfPerson(personList);
        int nbAdultes = (int) mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) >= 18).count();
        int nbEnfants = (int) mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) < 18).count();
        FireStationInfoDTO fireStationInfoDTO = new FireStationInfoDTO(personInfoList, nbEnfants, nbAdultes);
        return fireStationInfoDTO;
    }

    public List<Person> findListOfPersonByAddress(Address address) {
        List<Person> personList = personRepository.findByAddress_AddressName(address.getAddressName());
        return personList;
    }


}

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

@Service
public class PersonService implements IEmailService {

    private final PersonRepository personRepository;


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
    @Override
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
        Collection<FireStation> fireStationSet = fireStationService.findOneStationByAddress(person.getAddress());
        Set<Integer> stationSet = new HashSet<>();
        for(FireStation fireStation : fireStationSet) {
            stationSet.add(fireStation.getStation());
        }

        AllInfoPersonDTO dto = new AllInfoPersonDTO();

        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAddress(person.getAddress().getAddressName());
        dto.setCity(person.getCity());
        dto.setZip(person.getZip());
        dto.setPhone(person.getPhone());
        dto.setEmail(person.getEmail());
        dto.setBirthdate(medicalRecord.getBirthDate());
        dto.setMedications(medicationService.makeStringSetFromMedication(medicalRecord.getMedications()));
        dto.setAllergies(allergyService.makeStringSetFromAllergy(medicalRecord.getAllergies()));
        dto.setStation(stationSet);
        return dto;
    }

    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(String address) {
        return medicalRecordService.getFireURL(address);
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
    public int calculateAgewithLocalDate (LocalDate date) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(now, date);
        return Math.abs(period.getYears());
    }
    public ChildAlertDTO getChildAlertDTOfromTwoList(List<ChildrenDTO> childs, List<AdultDTO> adults) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setEnfants(childs);
        childAlertDTO.setAutresMenbresDuFoyer(adults);
        return childAlertDTO;
    }
    public ChildAlertDTO getChildrenAtThisAdress(String address) {
       List<Person> allPersoninAddress = personRepository.findByAddress_AddressName(address);
       List<MedicalRecord> mRList = medicalRecordService.getMedicalRecordByListOfPerson(allPersoninAddress);
       List<ChildrenDTO> childs = mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) < 18).map(ChildrenDTO::ChildrenfromMedicalRecord).collect(Collectors.toList());
       List<AdultDTO> adults = mRList.stream().filter(mr -> calculateAgewithLocalDate(mr.getBirthDate()) >= 18).map(AdultDTO::AdultfromMedicalRecord).collect(Collectors.toList());
        return getChildAlertDTOfromTwoList(childs,adults);
    }

    private List<FloodDTO> getListFloodDTOWithFireStationList(List<FireStation> fireStationList) {
        List<FloodDTO> floodDTOList = new ArrayList<>();
        for (FireStation f: fireStationList) {
            for (Address a : f.getAddress()) {
                List<Person> personList = findListOfPersonByAddress(a) ;
                List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecordByListOfPerson(personList);
                List<FloodContactDTO> floodContactDTOList = FloodContactDTO.fromListPersonMr(personList,medicalRecordList);
                FloodDTO floodDTO = new FloodDTO();
                floodDTO.setStation(f.getStation());
                floodDTO.setAddress(a.getAddressName());
                floodDTO.setContacts(floodContactDTOList);
                floodDTOList.add(floodDTO);
            }
        }
        return floodDTOList;
    }
    public List<Person> findListOfPersonByAddress(Address address) {
        List<Person> personList = personRepository.findByAddress_AddressName(address.getAddressName());
        return personList;
    }

    public List<FloodDTO> getFloodByListOfStation(List<Integer> stationList) {
        List<FireStation> fireStationList = fireStationService.getListFireStationByNb(stationList);
        List<FloodDTO> floodDTOList = getListFloodDTOWithFireStationList(fireStationList);
        return floodDTOList;

    }

}

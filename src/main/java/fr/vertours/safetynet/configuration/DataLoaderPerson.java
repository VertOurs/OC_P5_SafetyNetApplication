package fr.vertours.safetynet.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.*;

@Component
public class DataLoaderPerson {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataLoaderPerson.class);

    @Autowired
    PersonService personService;

    public Set<Person> returnSetOfPersonInPersonDTO(Map<String,
            Object> map, ObjectMapper objectMapper){
        Set<Person> personSet = new LinkedHashSet();
        List<Object> listOfPersonDTO = (List<Object>) map.get("persons");
        for(Object o : listOfPersonDTO) {
            PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);
            Person person = personDTO.createPerson();
            personSet.add(person);
        }
        return personSet;
    }
    public Set<Address> returnSetOfAddressInPersonDTO(Map<String,
            Object> map, ObjectMapper objectMapper){
        Set<Address> addressSet = new HashSet();
        List<Object> listOfPersonDTO = (List<Object>) map.get("persons");
        for(Object o : listOfPersonDTO) {
            PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);
            Address address = new Address();
            address.setAddressName(personDTO.getAddress());
            if (!addressSet.contains(address)) {
                addressSet.add(address);
            }
        }

        return addressSet;
    }

    public void savePersonAndAddressInDB (Map<String, Object> map,
                                          ObjectMapper objectMapper,
                                          List<Address> addressList,
                                          Set<Person> personSet) {
        for(Person person : personSet){
            Address personAddress = person.getAddress();

            Address address = addressList.stream().filter((addressa)->
                    addressa.getAddressName().equals(personAddress
                            .getAddressName())).findFirst().get();

            person.setAddress(address);
        }
        personService.saveAll(personSet);

    }
}

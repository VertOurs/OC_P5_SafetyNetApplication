package fr.vertours.safetynet.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.FireStationRepository;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import fr.vertours.safetynet.service.AddressService;
import fr.vertours.safetynet.service.FireStationService;
import fr.vertours.safetynet.service.MedicalRecordService;
import fr.vertours.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Configuration
public class DataBaseConfig {

    @Value("classpath:Json/data.json")
    Resource resource;

    @Autowired
    AddressService addressService ;
    @Autowired
    DataLoaderPerson personLoader;
    @Autowired
    DataLoaderFireStation fireStationLoader;
    @Autowired
    DataLoaderMedicalRecord medicalRecordLoader;


    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Map<String, Object> map = deserializeJson();

            ObjectMapper objectMapper = new ObjectMapper();
            Set<Address> addressSet = personLoader.
                    returnSetOfAddressInPersonDTO(map, objectMapper);
            Set<Person> personSet = personLoader.
                    returnSetOfPersonInPersonDTO(map, objectMapper);
            List<Address> addressList = addressService.saveAll(addressSet);

            personLoader.savePersonAndAddressInDB(map,
                    objectMapper, addressList,personSet);
            fireStationLoader.saveFireStationInDB(map,
                    objectMapper, addressList);
            medicalRecordLoader.saveMedicalRecordInDB(map, objectMapper);
        };
    }

    public Map<String, Object> deserializeJson() throws IOException {
        InputStream input = resource.getInputStream();
        String data = Files.readString(Paths.get(resource.getURI()));
        Map<String, Object> map = JsonIterator.deserialize(data, Map.class);
        return map;
    }
}

package fr.vertours.safetynet.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.dto.MedicalRecordDTO;
import fr.vertours.safetynet.dto.PersonDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.repository.FireStationRepository;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import fr.vertours.safetynet.service.AddressService;
import fr.vertours.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Configuration
public class DataBaseConfig {

    @Value("classpath:Json/data.json")
    Resource resource;

    @Autowired
    AddressService addressService;

    @Bean
    CommandLineRunner commandLineRunner(
            FireStationRepository fireStationRepository,
            MedicalRecordRepository medicalRecordRepository,
            PersonRepository personRepository) {

        return args -> {

            InputStream input = resource.getInputStream();
            String data = Files.readString(Paths.get(resource.getURI()));

            Map<String, Object> map = JsonIterator.deserialize(data, Map.class);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Object> listOfPersonDTO = (List<Object>) map.get("persons");
            Set<Address> addressSet = new HashSet();

            for(Object o : listOfPersonDTO) {
                PersonDTO personDTO = objectMapper.convertValue(o, PersonDTO.class);
                System.out.println(personDTO);
                Address address = new Address();
                address.setAddressName(personDTO.getAddress());
                if (!addressSet.contains(address)) {
                    addressSet.add(address);
                }
                //addressService.saveAddress(address);
            }
            addressService.saveAll(addressSet);


           //List<MedicalRecordDTO> listOfMedicalRecordDTO = (List<MedicalRecordDTO>) map.get("medicalrecords");
            //List<FireStationDTO> listOfFireStationDTO = (List<FireStationDTO>) map.get("firestations");


        };
    }

}

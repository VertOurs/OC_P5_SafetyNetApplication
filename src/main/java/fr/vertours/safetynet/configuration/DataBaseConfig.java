package fr.vertours.safetynet.configuration;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Configuration
public class DataBaseConfig {

    @Value("classpath:Json/data.json")
    Resource resource;
    
    @Bean
    CommandLineRunner commandLineRunner(
            FireStationRepository fireStationRepository,
            MedicalRecordRepository medicalRecordRepository,
            PersonRepository personRepository) {

        return args -> {

            InputStream input = resource.getInputStream();
            String data = Files.readString(Paths.get(resource.getURI()));

            Map<String, Object> map = JsonIterator.deserialize(data, Map.class);

            List<PersonDTO> listOfPersonDTO = (List<PersonDTO>) map.get("persons");
            for(PersonDTO personDTO : listOfPersonDTO){
                Address address = new Address();
                address.setAddressName(personDTO.getAddress());
                AddressService addressService;
                addressService.saveAddress(address);
            }


            List<MedicalRecordDTO> listOfMedicalRecordDTO = (List<MedicalRecordDTO>) map.get("medicalrecords");
            List<FireStationDTO> listOfFireStationDTO = (List<FireStationDTO>) map.get("firestations");
        };
    }

}

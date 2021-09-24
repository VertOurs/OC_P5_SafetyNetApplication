package fr.vertours.safetynet.database;

import com.jsoniter.JsonIterator;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.FireStationRepository;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Stream;

@Configuration
public class DataBaseConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            FireStationRepository fireStationRepository,
            MedicalRecordRepository medicalRecordRepository,
            PersonRepository personRepository) {

        return args -> {
            List<Person> persons=JsonIterator.deserialize(Stream de mon Json,PersonRepository.class);
        };
    }

}

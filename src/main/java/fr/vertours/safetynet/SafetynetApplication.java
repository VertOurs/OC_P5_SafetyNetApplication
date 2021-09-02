package fr.vertours.safetynet;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.service.FireStationService;
import org.objectweb.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class SafetynetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafetynetApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner runner(FireStationService fireStationService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<FireStation>> typeReference = new TypeReference<List<FireStation>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data.json");
			try {
				List<FireStation> fireStations = mapper.readValue(inputStream, typeReference);
				FireStationService.create(firestations);
			} catch (IOException e){
				System.out.println(e.getMessage());
			}
		};

	}*/

	//JsonIterator.deserialize("[1,2,3]", map.class);

}

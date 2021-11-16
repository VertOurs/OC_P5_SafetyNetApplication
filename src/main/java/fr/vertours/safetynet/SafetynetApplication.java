package fr.vertours.safetynet;

import fr.vertours.safetynet.controller.PersonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetynetApplication {


	public static void main(String[] args) {
		SpringApplication.run(SafetynetApplication.class, args);

	}

}
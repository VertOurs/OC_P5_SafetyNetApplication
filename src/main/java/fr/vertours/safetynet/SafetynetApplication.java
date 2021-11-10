package fr.vertours.safetynet;

import fr.vertours.safetynet.controller.PersonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetynetApplication {

	private static Logger LOGGER = LogManager.getLogger(SafetynetApplication.class);

	public static void main(String[] args) {
		LOGGER.info("application initialized"); // sa ne marche qu'ici et j'ai pas de log.txt? Pourquoi je le renvoi deux fois?
		SpringApplication.run(SafetynetApplication.class, args);
		LOGGER.trace("Exiting application"); //pas de trace final?
	}

}

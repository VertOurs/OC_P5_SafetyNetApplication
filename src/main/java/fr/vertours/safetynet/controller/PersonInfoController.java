package fr.vertours.safetynet.controller;

import fr.vertours.safetynet.dto.PersonInfoDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.service.IPersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonInfoController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonInfoController.class);

    @Autowired
    IPersonInfoService iPersonInfoService;

    /**
     * Endpoint that allows to return the information of a person according to the requirements of the project.
     * @param firstName
     * @param LastName
     * @return personInfoDTO
     */
    @GetMapping("/personInfo")
    public ResponseEntity<PersonInfoDTO> getNameAddressAgeMailMedicationsAndAllergies(@RequestParam("firstName") String firstName, @RequestParam("lastName") String LastName) {
        LOGGER.info("call endpoint /personInfo");
        Person person = this.iPersonInfoService.find(firstName, LastName);
        MedicalRecord medicalRecord = new MedicalRecord(person);
        PersonInfoDTO personInfoDTO =  new PersonInfoDTO(person, medicalRecord);

        return ResponseEntity.ok().body(personInfoDTO);
    }
}

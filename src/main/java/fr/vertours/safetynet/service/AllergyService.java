package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.repository.AllergyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AllergyService {

    @Autowired
    private AllergyRepository allergyRepository;

    public Allergy find(String allergyName) {
        return allergyRepository.findOneByAllergy(allergyName);
    }

    public Allergy save(String allergyName) {
        Allergy allergy = new Allergy();
        allergy.setAllergy(allergyName);
        return save(allergy);
    }

    public Allergy save( Allergy allergy) {
        return allergyRepository.save(allergy);
    }

}

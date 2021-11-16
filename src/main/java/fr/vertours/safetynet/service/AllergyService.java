package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.repository.AllergyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public Allergy findOrCreate(String allergyName) {
        Allergy allergy = find(allergyName);
        if (allergy == null) {
            allergy = save(allergyName);
        }
        return allergy;
    }

    public Set<String> makeStringSetFromAllergy(Set<Allergy> medicationSet) {
        Set<String> stringSet = new HashSet<>();
        for(Allergy a : medicationSet) {
            String stringMedication = a.getAllergy();
            stringSet.add(stringMedication);
        }
        return stringSet;
    }

}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.model.Allergy;
import fr.vertours.safetynet.model.Medication;
import fr.vertours.safetynet.repository.MedicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MedicationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);

    @Autowired
    MedicationRepository medicationRepository;

    public Medication find(String medicationName){
        return medicationRepository.findOneByMedication(medicationName);
    }

    public Medication save(String medicationName) {
        Medication medication = new Medication();
        medication.setMedication(medicationName);
        return save(medication);
    }

    public Medication save( Medication medication) {
        return medicationRepository.save(medication);
    }

    public Medication findOrCreate(String medicationName) {
        Medication medication = find(medicationName);
        if (medication == null) {
            medication = save(medicationName);
        }
        return medication;
    }

    public Set<String> makeStringSetFromMedication(Set<Medication> medicationSet) {
        Set<String> stringSet = new HashSet<>();
        for(Medication m : medicationSet) {
            String stringMedication = m.getMedication();
            stringSet.add(stringMedication);
        }
        return stringSet;
    }
}

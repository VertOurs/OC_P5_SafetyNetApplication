package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PhoneAlertService implements IPhoneAlertService {



    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    /**
     * find list of person by Station number.
     * @param station
     * @return a list of Person entity.
     */
    @Override
    public List<Person> findByStation(int station) {

        FireStation fireStation = fireStationService.findOneStation(station);
        Set<Address> addressSet = fireStation.getAddress();
        return personService.findByAddressIn(addressSet);
    }
}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;

import java.util.List;

public interface IPhoneAlertService {
    List<Person> findByStation(int station);
}

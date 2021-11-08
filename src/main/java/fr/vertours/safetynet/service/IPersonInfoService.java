package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Person;

public interface IPersonInfoService {

    Person find(String FirstName, String lastName);
}

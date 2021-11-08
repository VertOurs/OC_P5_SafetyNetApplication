package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireDTO;

import java.util.List;

public interface IFireService {


    public List<FireDTO> getListOfPersonForOneAddressWithFireStation(String address);
}

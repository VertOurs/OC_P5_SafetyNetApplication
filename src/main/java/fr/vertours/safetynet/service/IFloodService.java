package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FloodDTO;

import java.util.List;

public interface IFloodService {

    List<FloodDTO> getFloodByListOfStation(List<Integer> stationList);
}

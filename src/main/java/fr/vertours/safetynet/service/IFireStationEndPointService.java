package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireStationInfoDTO;

public interface IFireStationEndPointService {

    FireStationInfoDTO getFireStationInfoDTOfromStationNumber(int stationNumber);
}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.ChildAlertDTO;
import org.springframework.stereotype.Service;

public interface IChildAlertService {

    ChildAlertDTO getChildrenAtThisAdress(String address);
}

package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.AdultDTO;
import fr.vertours.safetynet.dto.ChildAlertDTO;
import fr.vertours.safetynet.dto.ChildrenDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
import fr.vertours.safetynet.repository.MedicalRecordRepository;
import fr.vertours.safetynet.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static fr.vertours.safetynet.util.CustomTools.calculateAgewithLocalDate;

@Service
public class ChildAlertService implements IChildAlertService {

    @Autowired
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Override
    public ChildAlertDTO getChildrenAtThisAdress(String address) {
        List<Person> allPersoninAddress =  personService.findByAddress(address);
        List<MedicalRecord> mRList =
                medicalRecordService.
                        getMedicalRecordByListOfPerson(allPersoninAddress);
        List<ChildrenDTO> childs =
                mRList.stream().
                        filter(mr -> calculateAgewithLocalDate(
                                mr.getBirthDate()) < 18).
                        map(ChildrenDTO::ChildrenfromMedicalRecord).
                        collect(Collectors.toList());
        List<AdultDTO> adults =
                mRList.stream().
                        filter(mr -> calculateAgewithLocalDate(
                                mr.getBirthDate()) >= 18).
                        map(AdultDTO::AdultfromMedicalRecord).
                        collect(Collectors.toList());
        return getChildAlertDTOfromTwoList(childs,adults);
    }

    private ChildAlertDTO getChildAlertDTOfromTwoList(List<ChildrenDTO> childs,
                                                      List<AdultDTO> adults) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setEnfants(childs);
        childAlertDTO.setAutresMenbresDuFoyer(adults);
        return childAlertDTO;
    }
}

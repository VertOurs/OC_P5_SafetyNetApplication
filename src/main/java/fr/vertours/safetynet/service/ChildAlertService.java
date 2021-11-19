package fr.vertours.safetynet.service;


import fr.vertours.safetynet.dto.AdultDTO;
import fr.vertours.safetynet.dto.ChildAlertDTO;
import fr.vertours.safetynet.dto.ChildrenDTO;
import fr.vertours.safetynet.model.MedicalRecord;
import fr.vertours.safetynet.model.Person;
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

    public ChildAlertService() {
    }

    public ChildAlertService(PersonService personService, MedicalRecordService medicalRecordService) {
        this.personService = personService;
        this.medicalRecordService = medicalRecordService;
    }

    /**
     * Create a ChildAlertDTO with an address.
     * @param address
     * @return ChildAlertDTO.
     */
    @Override
    public ChildAlertDTO getChildrenAtThisAdress(String address) {

        List<MedicalRecord> mRList = medicalRecordsByAddress(address);
        List<ChildrenDTO> childs = childrenDTOListByMedicalRecordList(mRList);
        List<AdultDTO> adults = adultDTOListByMedicalRecordList(mRList);
        return getChildAlertDTOfromTwoList(childs,adults);
    }

    /**
     * create a ChildAlertDTO.
     * @param childs
     * @param adults
     * @return a ChildAlertDTO.
     */
    private ChildAlertDTO getChildAlertDTOfromTwoList(List<ChildrenDTO> childs,
                                                      List<AdultDTO> adults) {
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setEnfants(childs);
        childAlertDTO.setAutresMenbresDuFoyer(adults);
        return childAlertDTO;
    }

    private List<MedicalRecord> medicalRecordsByAddress(String address){
        List<Person> allPersonInAddress =  personService.findByAddress(address);
        List<MedicalRecord> medicalRecordList = medicalRecordService.
                getMedicalRecordByListOfPerson(allPersonInAddress);
        return medicalRecordList;
    }

    private List<ChildrenDTO> childrenDTOListByMedicalRecordList(List<MedicalRecord> list) {
        List<ChildrenDTO> childList = list.stream().
                filter(mr -> calculateAgewithLocalDate(
                        mr.getBirthDate()) < 18).
                map(ChildrenDTO::ChildrenfromMedicalRecord).
                collect(Collectors.toList());
        return childList;
    }
    private List<AdultDTO> adultDTOListByMedicalRecordList(List<MedicalRecord> list) {
        List<AdultDTO> adultList = list.stream().
                filter(mr -> calculateAgewithLocalDate(
                        mr.getBirthDate()) >= 18).
                map(AdultDTO::AdultfromMedicalRecord).
                collect(Collectors.toList());
        return adultList;
    }
}

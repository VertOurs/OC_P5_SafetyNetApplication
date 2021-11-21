package fr.vertours.safetynet.service;

import fr.vertours.safetynet.dto.FireStationDTO;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;
import fr.vertours.safetynet.repository.FireStationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

@SpringBootTest
class FireStationServiceTest {

    FireStationRepository fireStationRepository = mock(FireStationRepository.class);
    AddressService addressService = mock(AddressService.class);

    FireStationService classUnderTest = new FireStationService(fireStationRepository, addressService);


    FireStation fireStation = new FireStation();
    Address address = new Address("100, rue pasteur");
    Set<Address> addressSet = new HashSet<>();
    FireStationDTO dto = new FireStationDTO();
    List<FireStation> fireStationList = new ArrayList<>();
    List<Integer> stationNumberList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        addressSet.add(address);
        fireStation.setStation(50);
        fireStation.setAddress(addressSet);
        dto = FireStationDTO.fromFireStation(fireStation);
        fireStationList.add(fireStation);
        stationNumberList.add(15);

    }

    @Test
    void saveOneStation() {
        when(fireStationRepository.findByStation(50))
                .thenReturn(null);
        when(addressService.findOrCreate(address.getAddressName()))
                .thenReturn(address);

        classUnderTest.saveOneStation(dto);

        verify(fireStationRepository,
                times(1)).findByStation(50);
        verify(fireStationRepository, times(1)).save(fireStation);
    }

    @Test
    void saveAllStations() {
        when(fireStationRepository.saveAll(fireStationList))
                .thenReturn(fireStationList);

        classUnderTest.saveAllStations(fireStationList);

        verify(fireStationRepository,times(1)).saveAll(fireStationList);

    }

    @Test
    void getListOfAllStations() {
        when(fireStationRepository.findAll()).thenReturn(fireStationList);

        classUnderTest.getListOfAllStations();

        verify(fireStationRepository, times(1)).findAll();
    }

    @Test
    void findOneStation() {
        when(fireStationRepository.findByStation(50)).thenReturn(fireStation);
        classUnderTest.findOneStation(50);
        verify(fireStationRepository, times(2))
                .findByStation(50);
    }

    @Test
    void findOneStationByAddress() {
        when(fireStationRepository.findFireStationByAddress(address))
                .thenReturn(fireStationList);
        classUnderTest.findOneStationByAddress(address);
        verify(fireStationRepository, times(1))
                .findFireStationByAddress(address);
    }

    @Test
    void deleteOneFireStation() {
        when(fireStationRepository.findByStation(50))
                .thenReturn(fireStation);
        doNothing().when(fireStationRepository).delete(fireStation);

        classUnderTest.deleteOneFireStation(50);
        verify(fireStationRepository, times(2))
                .findByStation(50);
        verify(fireStationRepository, times(1))
                .delete(fireStation);
    }

    @Test
    void updateStationForOneAddress() {
        when(fireStationRepository.findByStation(50))
                .thenReturn(fireStation);
        when(addressService.findOrCreate(address.getAddressName()))
                .thenReturn(address);
        when(fireStationRepository.save(fireStation)).thenReturn(fireStation);

        classUnderTest.updateStationForOneAddress(
                fireStation.getStation(),address);

        verify(fireStationRepository, times(2))
                .findByStation(50);
        verify(addressService, times(1))
                .findOrCreate(address.getAddressName());
        verify(fireStationRepository, times(1))
                .save(fireStation);
    }

    @Test
    void getListFireStationByNb() {
        when(fireStationRepository.findByStationIn(stationNumberList))
                .thenReturn(fireStationList);

        classUnderTest.getListFireStationByNb(stationNumberList);

        verify(fireStationRepository, times(1))
                .findByStationIn(stationNumberList);
    }
}
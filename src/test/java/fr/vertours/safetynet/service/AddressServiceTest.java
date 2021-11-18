package fr.vertours.safetynet.service;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.repository.AddressRepository;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class AddressServiceTest {

    AddressRepository addressRepository = mock(AddressRepository.class);

    AddressService classUnderTest = new AddressService(addressRepository);


    List<Address>   addressList = new ArrayList<>();
    String stringAddress = "15, rue du port";
    Address address1 = new Address(stringAddress);

    @BeforeEach
    void setUp() {


        addressList.add(address1);
    }

    @Test
    void saveAll() {

        when(addressRepository.saveAll(addressList)).thenReturn(addressList);
        List<Address> listTested = classUnderTest.saveAll(addressList);
        assertEquals(addressList,listTested);
    }

    @Test
    void find() {
        when(addressRepository.findOneByAddressName(stringAddress))
                .thenReturn(address1);

        Address addressTested = classUnderTest.find(stringAddress);

        assertEquals(address1, addressTested);
    }

    @Test
    void save() {
        when(addressRepository.save(address1)).thenReturn(address1);
        Address addressTested = classUnderTest.save(address1);

        assertEquals(address1, addressTested);
        assertEquals(address1.getAddressName(), addressTested.getAddressName());
    }

    @Test
    void findOrCreateByFindProbability() {
        when(addressRepository.findOneByAddressName(stringAddress))
                .thenReturn(address1);

        Address addressTested = classUnderTest.findOrCreate(stringAddress);
        assertEquals(address1,addressTested);
        assertEquals(address1.getAddressName(), addressTested.getAddressName());
    }
}
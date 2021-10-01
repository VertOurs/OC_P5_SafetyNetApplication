package fr.vertours.safetynet.service;


import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public void saveAddress(Address address){

            addressRepository.save(address);

    }

    public void saveAll(Collection<Address> collection) {
        addressRepository.saveAll(collection);
    }
}

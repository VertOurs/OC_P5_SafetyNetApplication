package fr.vertours.safetynet.service;


import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public void saveAddress(Address address){

            addressRepository.save(address);

    }

    public List<Address> saveAll(Collection<Address> collection) {
        return addressRepository.saveAll(collection);

    }
}

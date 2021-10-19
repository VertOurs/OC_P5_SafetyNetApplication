package fr.vertours.safetynet.service;


import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

    public Address find(String addressName) {
        return addressRepository.findOneByAddressName(addressName);
    }

    public Address save(Address address) {
        return addressRepository.save(address);
    }
    public Address save(String addressName) {
        Address address = new Address();
        address.setAddressName(addressName);
        return save(address);
    }
}

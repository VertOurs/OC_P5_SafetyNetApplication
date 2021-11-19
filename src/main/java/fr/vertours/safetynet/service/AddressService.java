package fr.vertours.safetynet.service;


import fr.vertours.safetynet.controller.ChildAlertController;
import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.exceptions.AddressNotFoundException;
import fr.vertours.safetynet.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressService {


    private final AddressRepository addressRepository;


    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> saveAll(Collection<Address> collection) {
        return addressRepository.saveAll(collection);

    }

    /**
     * Find an address.
     * @param addressName
     * @return An Address entity.
     */
    public Address find(String addressName) {
//        Optional<Address> existingAddress = Optional.ofNullable(addressRepository.findOneByAddressName(addressName));
//        if (existingAddress.isEmpty()) {
//            throw new AddressNotFoundException(addressName);
//        }
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

    public Address findOrCreate(String addessName) {
        Address address = find(addessName);
        if(address == null) {
            address = save(addessName);
        }
        return address;
    }
}

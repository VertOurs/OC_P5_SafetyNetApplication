package fr.vertours.safetynet.service;


import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public void saveAddress(Address address){
        try {
            addressRepository.save(address);
        }
        catch (IllegalArgumentException e){
            
        }
    }
}

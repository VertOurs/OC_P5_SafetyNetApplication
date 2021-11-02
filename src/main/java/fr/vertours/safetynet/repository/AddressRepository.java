package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Address findOneByAddressName(String addressName);


    
}

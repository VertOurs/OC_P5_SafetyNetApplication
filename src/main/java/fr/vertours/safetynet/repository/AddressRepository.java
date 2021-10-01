package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    //pk utilisé JpaRepository et pas CrudRepository : https://www.baeldung.com/spring-data-crud-repository-save
    
}

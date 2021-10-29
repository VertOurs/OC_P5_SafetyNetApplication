package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findByFirstName(String firstName);
    List<Person> findByLastName(String lastName);
    Person findOneByFirstNameAndLastName(String firstName, String lastName);
    Person findOneById(Long ID);
    Person deleteByFirstNameAndLastName(String firstName, String lastName);
    List<Person> findByAddress_AddressName(String address);
// AU debut j'avais essayé une methode findByCity sans plus de résultat.
    List<Person> findAllByCity(String city);
    List<Person> findByAddressIn(Collection<Address> addresseCollections);



}

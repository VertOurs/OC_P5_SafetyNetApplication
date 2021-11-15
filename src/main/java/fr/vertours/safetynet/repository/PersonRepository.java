package fr.vertours.safetynet.repository;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;


public interface PersonRepository extends JpaRepository<Person, Long> {

   Person findOneByFirstNameAndLastName(String firstName, String lastName) throws NullPointerException;
   List<Person> findByAddress_AddressName(String address);
   List<Person> findAllByCity(String city);
   List<Person> findByAddressIn(Collection<Address> addressCollection);




}

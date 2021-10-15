package fr.vertours.safetynet.model;

import org.codehaus.plexus.classworlds.strategy.Strategy;

import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table (uniqueConstraints = {
        @UniqueConstraint(columnNames = {"firstName", "lastName"})
})
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String lastName;

    @ManyToOne
    @JoinColumn (nullable = false)
    private Address address;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String zip;

    @Column (nullable = false)
    private String phone;

    @Column (nullable = false)
    private String email;

    public Person(String firstName, String lastName, Address address, String city, String zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }
    public Person(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

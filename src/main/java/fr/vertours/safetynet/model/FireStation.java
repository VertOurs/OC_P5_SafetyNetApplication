package fr.vertours.safetynet.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class FireStation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private int station;

    @ManyToMany
    @JoinTable (
            name = "FireStation_Address",
            joinColumns =  @JoinColumn (name = "FireStation"),
            inverseJoinColumns = @JoinColumn (name = "Address")
    )
    private Set<Address> address;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
        for (Address address1 : this.address ) {
            address1.setFireStation(this);
        }
    }
}

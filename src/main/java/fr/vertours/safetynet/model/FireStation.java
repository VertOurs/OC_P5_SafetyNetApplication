package fr.vertours.safetynet.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
public class FireStation {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private int station;

    @ManyToMany (mappedBy = "fireStation")
    private Set<Address> address;

    public FireStation() {
        this.address = new HashSet<>();
    }

    public void addAdress(Address address) {
        if (!this.address.contains(address)) {
            address.addFirestation(this);
            this.address.add(address);
        }
    }

    public void removeAddress(Address address) {
        if (this.address.contains(address)) {
            address.removeFirestation(this);
            this.address.remove(address);
        }
    }

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

    public void setAddress(Collection<Address> address) {
        this.address = new HashSet<>(address);
        for (Address address1 : this.address ) {
            address1.addFirestation(this);
        }
    }
}

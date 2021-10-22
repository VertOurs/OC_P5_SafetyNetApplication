package fr.vertours.safetynet.model;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class FireStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private int station;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Address> address;

    public FireStation() {
        this.address = new HashSet<>();
    }
    public FireStation(Set<Address> address, int station ) {
        this.address = address;
        this.station = station;
    }

    public void addAdress(Address address) {
        if (!this.address.contains(address)) {
            this.address.add(address);
            address.addFirestation(this);
        }
    }

    public void removeAddress(Address address) {
        if (this.address.contains(address)) {
            this.address.remove(address);
            address.removeFirestation(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireStation that = (FireStation) o;
        if(id==null) {
            return station == that.station;
        }
        return station == that.station ||
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, station);
    }

    @Override
    public String toString() {
        return "FireStation{" +
                "id=" + id +
                ", station=" + station +
                '}';
    }
}

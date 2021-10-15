package fr.vertours.safetynet.model;


import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String addressName;


    @ManyToMany
    private Set<FireStation> fireStation;

    public Address(String addressName) {

        this.addressName = addressName;
        this.fireStation = new HashSet<>();
    }
    public Address() {
        this.fireStation = new HashSet<>();
    }

    public void addFirestation(FireStation fireStation){
        if (!this.fireStation.contains(fireStation)) {
            this.fireStation.add(fireStation);
            fireStation.addAdress(this);
        }
    }

    public void removeFirestation(FireStation fireStation) {
        if (this.fireStation.contains(fireStation)) {
            this.fireStation.remove(fireStation);
            fireStation.removeAddress(this);
        }
    }
    public Set<FireStation> getFireStation() {
        return fireStation;
    }

    public void setFireStation(Collection<FireStation> fireStation) {
        this.fireStation = new HashSet<>(fireStation);
        for(FireStation fireStation1 : this.fireStation) {
            fireStation1.addAdress(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) ||
                addressName.equals(address.addressName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressName);
    }
}

package fr.vertours.safetynet.model;


import javax.persistence.*;

@Entity
public class Address {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String addressName;

    @JoinColumn(nullable = false, unique = true)
    @ManyToMany
    private FireStation fireStation;

    public Address(String addressName) {
        this.addressName = addressName;
    }
    public Address() {
        
    }

    public FireStation getFireStation() {
        return fireStation;
    }

    public void setFireStation(FireStation fireStation) {
        this.fireStation = fireStation;
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
}

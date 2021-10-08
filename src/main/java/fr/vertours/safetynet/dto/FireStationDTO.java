package fr.vertours.safetynet.dto;

import fr.vertours.safetynet.model.Address;
import fr.vertours.safetynet.model.FireStation;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FireStationDTO {

    // es ce que je passe un string adresse ou alors un objet adress?
    private Set<String> address;
    private int station;

    @Override
    public String toString() {
        return "FireStationDTO{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }

    public FireStation createFireStation(){
        Set<Address> setAddress = address.stream().map(Address::new).collect(Collectors.toSet());
        FireStation fireStation = new FireStation(setAddress, getStation());
        return fireStation;
    }

    public Set<String> getAddress() {
        return address;
    }

    public void setAddress(Set<String> address) {
        this.address = address;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }
}

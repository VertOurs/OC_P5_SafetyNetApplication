package fr.vertours.safetynet.dto;

import java.util.List;

public class FloodDTO {

    String station;
    String address;
    List<FloodContactDTO> contacts;



    public String getStation() {
        return station;
    }
    public void setStation(String station) {
        this.station = station;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public List<FloodContactDTO> getContacts() {
        return contacts;
    }
    public void setContacts(List<FloodContactDTO> contacts) {
        this.contacts = contacts;
    }
}

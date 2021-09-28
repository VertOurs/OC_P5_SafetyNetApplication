package fr.vertours.safetynet.dto;

public class FireStationDTO {

    // es ce que je passe un string adresse ou alors un objet adress?
    private String address;
    private String station;

    @Override
    public String toString() {
        return "FireStationDTO{" +
                "address='" + address + '\'' +
                ", station=" + station +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}

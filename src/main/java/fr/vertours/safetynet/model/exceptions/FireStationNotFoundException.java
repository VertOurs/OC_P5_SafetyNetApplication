package fr.vertours.safetynet.model.exceptions;

public class FireStationNotFoundException extends RuntimeException {
    private final int station;

    public FireStationNotFoundException(int station)  {
        this.station = station;
    }

    @Override
    public String getMessage() {
        return "The FireStation n° "+station+", was not found in database";
    }
}

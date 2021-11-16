package fr.vertours.safetynet.model.exceptions;

public class FireStationAlreadyPresentException extends RuntimeException {
    private final int station;

    public FireStationAlreadyPresentException(int station) {
        this.station = station;
    }

    @Override
    public String getMessage() {
        return "The FireStation \"" + station + "\", is already present in Database.";
    }
}

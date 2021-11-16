package fr.vertours.safetynet.model.exceptions;

public class AddressNotFoundException extends RuntimeException {
    private final String addressName;

    public AddressNotFoundException(String adressName) {
        this.addressName = adressName;
    }
    @Override
    public String getMessage() {
        return "The address \""+addressName+"\", was not found in database";
    }

}

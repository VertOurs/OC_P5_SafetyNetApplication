package fr.vertours.safetynet.model.exceptions;

public class PersonAlreadyPresentException extends RuntimeException {
    private final String firstName;
    private final String lastName;

    public PersonAlreadyPresentException(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String getMessage() {
        return "The person \"" + firstName + " " + lastName + "\", is already present in Database.";
    }
}

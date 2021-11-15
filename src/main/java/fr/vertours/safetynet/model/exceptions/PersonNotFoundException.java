package fr.vertours.safetynet.model.exceptions;

public class PersonNotFoundException extends RuntimeException {
    private final String firstName;
    private final String lastName;




    public PersonNotFoundException( String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Override
    public String getMessage() {
        return "The person \"" + firstName + " " + lastName + "\", was not found in DataBase.";
    }
}

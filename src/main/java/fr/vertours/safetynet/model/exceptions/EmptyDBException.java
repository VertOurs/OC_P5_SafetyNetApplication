package fr.vertours.safetynet.model.exceptions;

public class EmptyDBException extends RuntimeException {


    @Override
    public String getMessage() {
        return "The application did not find anything in the database.\nIf this is not the expected behavior, please contact your administrator.";
    }
}

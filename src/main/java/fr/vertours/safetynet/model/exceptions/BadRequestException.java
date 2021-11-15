package fr.vertours.safetynet.model.exceptions;

public class BadRequestException extends RuntimeException {

    private String message;

    public static BadRequestException createWith(String message) {
        return new BadRequestException(message);
    }

    private BadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message + " not found";
    }
}

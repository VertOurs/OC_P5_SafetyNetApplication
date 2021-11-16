package fr.vertours.safetynet.controller.exceptions;

import fr.vertours.safetynet.model.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(FireStationNotFoundException.class)
    public final ResponseEntity<String> handleFireStationNotFoundException(FireStationNotFoundException e) {
        LOGGER.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(FireStationAlreadyPresentException.class)
    public final ResponseEntity<String> handleFireStationAlreadyPresentException(FireStationAlreadyPresentException e) {
        LOGGER.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
    @ExceptionHandler(PersonNotFoundException.class)
    public final ResponseEntity<String> handlePersonNotFoundException(PersonNotFoundException e) {
        LOGGER.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(PersonAlreadyPresentException.class)
    public final ResponseEntity<String> handlePersonAlreadyPresentException(PersonAlreadyPresentException e) {
        LOGGER.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

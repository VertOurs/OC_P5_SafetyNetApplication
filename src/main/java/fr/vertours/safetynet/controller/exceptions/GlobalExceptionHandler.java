package fr.vertours.safetynet.controller.exceptions;

import fr.vertours.safetynet.model.exceptions.BadRequestException;
import fr.vertours.safetynet.model.exceptions.EmptyDBException;
import fr.vertours.safetynet.model.exceptions.PersonAlreadyPresentException;
import fr.vertours.safetynet.model.exceptions.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({BadRequestException.class, PersonNotFoundException.class, PersonAlreadyPresentException.class})
    public final ResponseEntity<String> handleException(Exception e) {

        LOGGER.error("Handling " + e.getClass().getSimpleName() + " due to " + e.getMessage() + ". More informations : "+e.getStackTrace());

        if(e instanceof BadRequestException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BadRequestException brE = (BadRequestException) e;
            return ResponseEntity.status(status).body(brE.getMessage());
        } else if (e instanceof PersonNotFoundException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            PersonNotFoundException pnfE = (PersonNotFoundException) e;
            return ResponseEntity.status(status).body(pnfE.getMessage());
        } else if (e instanceof EmptyDBException) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            EmptyDBException ebdE = (EmptyDBException) e;
            return ResponseEntity.status(status).body(ebdE.getMessage());
        } else if (e instanceof PersonAlreadyPresentException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            PersonAlreadyPresentException papE = (PersonAlreadyPresentException) e;
            return ResponseEntity.status(status).body(papE.getMessage());
    } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body(e.getMessage() + e.getStackTrace());


                    //"The application has a problem with your request, make sure your request is valid. \nIf the problem persists, please contact your administrator.");
        }
    }


}

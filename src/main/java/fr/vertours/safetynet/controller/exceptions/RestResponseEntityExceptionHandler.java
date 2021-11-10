package fr.vertours.safetynet.controller.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackages = "fr.vertours.safetynet.controller")
public class RestResponseEntityExceptionHandler extends RuntimeException {

    @ExceptionHandler(NullPointerException.class)
    public String nullPointerHandledException() {
        return "problème";
    }


}

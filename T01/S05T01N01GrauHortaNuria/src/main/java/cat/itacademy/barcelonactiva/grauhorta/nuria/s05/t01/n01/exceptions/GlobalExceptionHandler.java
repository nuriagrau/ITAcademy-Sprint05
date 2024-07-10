package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n01.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SucursalNotFoundException.class)
    public ResponseEntity<ErrorMessage> sucursalNotFoundException(SucursalNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));

        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SucursalAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> sucursalAlreadyExistsException(SucursalAlreadyExistsException ex, WebRequest webRequest) {
        ErrorMessage errorMessage =  new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }
}

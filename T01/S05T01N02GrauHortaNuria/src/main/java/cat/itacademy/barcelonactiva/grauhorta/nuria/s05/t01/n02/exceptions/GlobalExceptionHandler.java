package cat.itacademy.barcelonactiva.grauhorta.nuria.s05.t01.n02.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ExceptionHandler(FlorNotFoundException.class)
    public ResponseEntity<ErrorMessage> florNotFoundException(FlorNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlorAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> florAlreadyExistsException(FlorAlreadyExistsException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmptyFlorListException.class)
    public ResponseEntity<ErrorMessage> emptyFlowerListException(EmptyFlorListException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NO_CONTENT.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NO_CONTENT);
    }
}

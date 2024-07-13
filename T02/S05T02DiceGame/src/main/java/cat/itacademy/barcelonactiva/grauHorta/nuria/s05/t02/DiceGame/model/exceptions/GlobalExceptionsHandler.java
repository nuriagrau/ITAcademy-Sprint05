package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

public class GlobalExceptionsHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleGlobalException(Exception ex, WebRequest webRequest) {
        ErrorMessage errorResponse = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                "Internal Server Error",
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EmptyGamesListException.class)
    public ResponseEntity<ErrorMessage> emptyGamesListException(EmptyGamesListException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmptyPlayersListException.class)
    public ResponseEntity<ErrorMessage> emptyPlayersListException(EmptyPlayersListException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> playerAlreadyExistsException(PlayerAlreadyExistsException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerNotFoundException.class)
    public ResponseEntity<ErrorMessage> playerAlreadyExistsException(PlayerNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> playerAlreadyExistsException(UserAlreadyExistsException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> playerAlreadyExistsException(UserNotFoundException ex, WebRequest webRequest) {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

}
package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GlobalExceptionsHandler extends ResponseEntityExceptionHandler {

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


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->{
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Map<String, Object>> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("error", "Forbidden");
        body.put("message", "Access Denied");
        body.put("path", request.getRequestURI());

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

}

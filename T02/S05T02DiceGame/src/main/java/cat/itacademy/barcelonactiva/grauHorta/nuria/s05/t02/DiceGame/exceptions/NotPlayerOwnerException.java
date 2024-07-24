package cat.itacademy.barcelonactiva.grauHorta.nuria.s05.t02.DiceGame.exceptions;

public class NotPlayerOwnerException extends RuntimeException{
    public NotPlayerOwnerException(String message) {
        super(message);
    }
}

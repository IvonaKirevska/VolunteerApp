package mk.ukim.finki.kiii.volunteerapp.model.exceptions;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
        super("The password is incorrect.");
    }
}

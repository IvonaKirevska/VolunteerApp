package mk.ukim.finki.kiii.volunteerapp.model.exceptions;

public class AccessDeniedException extends Exception{
    public AccessDeniedException() {
        super("You are not the OWNER of this workspace");
    }
}

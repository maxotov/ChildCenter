package kz.enu.fit.logic.exceptions;

/**
 *
 * @author Aibol
 */
public class IncorrectEmailException extends Exception{

    public IncorrectEmailException() {
    }

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectEmailException(Throwable cause) {
        super(cause);
    }
    
}

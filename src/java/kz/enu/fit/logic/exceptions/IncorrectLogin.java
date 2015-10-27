package kz.enu.fit.logic.exceptions;

/**
 *
 * @author Aibol
 */
public class IncorrectLogin extends Exception{

    public IncorrectLogin() {
    }

    public IncorrectLogin(String message) {
        super(message);
    }

    public IncorrectLogin(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLogin(Throwable cause) {
        super(cause);
    }
    
}

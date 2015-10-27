package kz.enu.fit.logic.exceptions;


public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectPasswordException(Throwable cause) {
        super(cause);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException() {
    }
    
    
    
}

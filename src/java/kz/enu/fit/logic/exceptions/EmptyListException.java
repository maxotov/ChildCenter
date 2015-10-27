package kz.enu.fit.logic.exceptions;


public class EmptyListException extends Exception{

    public EmptyListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmptyListException(Throwable cause) {
        super(cause);
    }

    public EmptyListException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyListException(String message) {
        super(message);
    }

    public EmptyListException() {
    }
    
    
    
}

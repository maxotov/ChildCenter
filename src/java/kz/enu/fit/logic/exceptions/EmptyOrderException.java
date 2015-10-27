package kz.enu.fit.logic.exceptions;

public class EmptyOrderException extends Exception {

    public EmptyOrderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public EmptyOrderException(Throwable cause) {
        super(cause);
    }

    public EmptyOrderException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyOrderException(String message) {
        super(message);
    }

    public EmptyOrderException() {
    }

    

    
    
}

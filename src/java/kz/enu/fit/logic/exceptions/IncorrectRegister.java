package kz.enu.fit.logic.exceptions;

public class IncorrectRegister extends Exception {

    public IncorrectRegister(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectRegister(Throwable cause) {
        super(cause);
    }

    public IncorrectRegister(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectRegister(String message) {
        super(message);
    }

    public IncorrectRegister() {
    }
    
}

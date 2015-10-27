package kz.enu.fit.logic.exceptions;

public class IncorrectDateFormat extends Exception {

    public IncorrectDateFormat(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectDateFormat(Throwable cause) {
        super(cause);
    }

    public IncorrectDateFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectDateFormat(String message) {
        super(message);
    }

    public IncorrectDateFormat() {
    }
    
}

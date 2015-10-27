package kz.enu.fit.logic.exceptions;

public class IncorrectInfoClient extends Exception {

    public IncorrectInfoClient(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectInfoClient(Throwable cause) {
        super(cause);
    }

    public IncorrectInfoClient(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectInfoClient(String message) {
        super(message);
    }

    public IncorrectInfoClient() {
    }
    
}

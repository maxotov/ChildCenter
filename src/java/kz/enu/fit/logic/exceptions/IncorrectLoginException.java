/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.enu.fit.logic.exceptions;

/**
 *
 * @author Aibol
 */
public class IncorrectLoginException extends Exception {

    public IncorrectLoginException() {
    }

    public IncorrectLoginException(String message) {
        super(message);
    }

    public IncorrectLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectLoginException(Throwable cause) {
        super(cause);
    }
    
}

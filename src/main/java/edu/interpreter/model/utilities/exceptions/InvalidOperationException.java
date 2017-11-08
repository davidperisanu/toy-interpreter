package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when a method call is invalid for the object's current state.
 * @author David Perisanu
 */
public final class InvalidOperationException extends RuntimeException {
    private static final long serialVersionUID = IdGenerator.generateLongId();

    /**
     * Initializes a new instance of the <code>InvalidOperationException</code> class.
     */
    public InvalidOperationException() {
        super();
    }

    /**
     * Initializes a new instance of the <code>InvalidOperationException</code> class with a specified error message.
     * @param message A message that describes the error.
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}

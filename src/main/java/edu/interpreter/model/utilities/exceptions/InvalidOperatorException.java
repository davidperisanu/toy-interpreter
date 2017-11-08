package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when there is an attempt to use an unknown operator.
 * @author David Perisanu
 */
public final class InvalidOperatorException extends RuntimeException {
    private static final long serialVersionUID = IdGenerator.generateLongId();

    /**
     * Initializes a new instance of the <code>InvalidOperatorException</code> class.
     */
    public InvalidOperatorException() {
        super();
    }

    /**
     * Initializes a new instance of the <code>InvalidOperatorException</code> class with a specified error message.
     * @param message A message that describes the error.
     */
    public InvalidOperatorException(String message) {
        super(message);
    }
}

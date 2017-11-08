package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when there is an attempt to divide an integral or decimal value by zero.
 * @author David Perisanu
 */
public final class DivideByZeroException extends RuntimeException {
    static final long serialVersionUID = IdGenerator.generateLongId();

    /**
     * Initializes a new instance of the <code>DivideByZeroException</code> class.
     */
    public DivideByZeroException() {
        super();
    }

    /**
     * Initializes a new instance of the <code>DivideByZeroException</code> class with a specified error message.
     * @param message A message that describes the error.
     */
    public DivideByZeroException(String message) {
        super(message);
    }
}

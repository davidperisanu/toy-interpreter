package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when there is an attempt to use something that cannot be found.
 * @author David Perisanu
 */
public final class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = IdGenerator.generateLongId();

    /**
     * Initializes a new instance of the <code>NotFoundException</code> class.
     */
    public NotFoundException() {
        super();
    }

    /**
     * Initializes a new instance of the <code>NotFoundException</code> class with a specified error message.
     * @param message A message that describes the error.
     */
    public NotFoundException(String message) {
        super(message);
    }
}

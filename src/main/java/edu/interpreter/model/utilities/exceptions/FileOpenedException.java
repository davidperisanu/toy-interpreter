package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when there is an attempt to open an already opened file.
 * @author David Perisanu
 */
public final class FileOpenedException extends RuntimeException {
    static final long serialVersionUID = IdGenerator.generateLongId();

    /**
     * Initializes a new instance of the <code>FileOpenedException</code> class.
     */
    public FileOpenedException() {
        super();
    }

    /**
     * Initializes a new instance of the <code>FileOpenedException</code> class with a specified error message.
     * @param message A message that describes the error.
     */
    public FileOpenedException(String message) {
        super(message);
    }
}

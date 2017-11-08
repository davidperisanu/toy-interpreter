package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when one of the arguments provided to a method is not valid.
 * @author David Perisanu
 */
public final class InvalidArgumentException extends RuntimeException {
    static final long serialVersionUID = IdGenerator.generateLongId();
    
        /**
         * Initializes a new instance of the <code>InvalidArgumentException</code> class.
         */
        public InvalidArgumentException() {
            super();
        }
    
        /**
         * Initializes a new instance of the <code>InvalidArgumentException</code> class with a specified error message.
         * @param message A message that describes the error.
         */
        public InvalidArgumentException(String message) {
            super(message);
        }
}

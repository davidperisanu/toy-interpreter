package edu.interpreter.model.utilities.exceptions;

import edu.interpreter.model.utilities.IdGenerator;

/**
 * The exception that is thrown when an attempt is made to access an element of an array or collection with an index that is outside its bounds.
 * @author David Perisanu
 */
public final class IndexOutOfRangeException extends RuntimeException {
    private static final long serialVersionUID = IdGenerator.generateLongId();

    /**
         * Initializes a new instance of the <code>IndexOutOfRangeException</code> class.
         */
        public IndexOutOfRangeException() {
            super();
        }
    
        /**
         * Initializes a new instance of the <code>IndexOutOfRangeException</code> class with a specified error message.
         * @param message A message that describes the error.
         */
        public IndexOutOfRangeException(String message) {
            super(message);
        }
}

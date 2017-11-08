package edu.interpreter.model.utilities;

/**
 * Generator of unique identifiers.
 * @author David Perisanu
 */
public final class IdGenerator {
    private static int id = 1;
    private static long longId = 1L;

    // Simulate static class.
    private IdGenerator() { }

    /**
     * Generates a unique integral identifier.
     * @return An integral that represents a unique identifier.
     */
    public static int generateId() {
        return id++;
    }

    /**
     * Generates a unique long integral identifier.
     * @return A long integral that represents a unique identifier.
     */
    public static long generateLongId() {
        return longId++;
    }
}

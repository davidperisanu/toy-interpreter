package edu.interpreter.model.utilities;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility for making deep copies of objects. Objects are first serialized and then 
 * deserialized.
 * 
 * @author David Perisanu
 * @author javatechniques.com
 */
public class DeepCopy {
    private DeepCopy() {}   // Simulate static class.

    /**
     * Returns a copy of the object, or null if the object cannot be serialized.
     * 
     * @return  The copy of the object.
     * @throws  IOException If an I/O error has occurred.
     * @throws  ClassNotFoundException If class of a serialized object cannot be found.
     */
    public static Object copy(Object object) throws IOException, ClassNotFoundException {
        Object newObject;
        OptimizedByteArrayOutputStream byteArrayOS;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;

        newObject = null;

        try {
            byteArrayOS = new OptimizedByteArrayOutputStream();
            outputStream = new ObjectOutputStream(byteArrayOS);

            // Write the object out to a byte array.
            outputStream.writeObject(object);
            outputStream.flush();
            outputStream.close();

            // Retrieve an input stream from the byte array and read a copy of the object 
            // back in.
            inputStream = new ObjectInputStream(byteArrayOS.inputStream());
            newObject = inputStream.readObject();
        }
        catch (IOException e) {
            throw new IOException("Copy of the object could not be created.");
        }
        catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Copy of the object could not be created.");
        }

        return newObject;
    }
}

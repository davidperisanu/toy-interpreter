package edu.interpreter.model.utilities;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Implementation of <code>ByteArrayOutputStream</code> that doesn't synchronize methods and 
 * doesn't copy the data on toByteArray().
 * 
 * @author David Perisanu
 * @author javatechniques.com
 */
public class OptimizedByteArrayOutputStream extends OutputStream {
    protected byte[] buffer;    // Byte buffer.
    protected int size;         // Buffer capacity.

    /**
     * Initializes a new instance of the <code>OptimizedByteArrayOutputStream</code> class 
     * that is empty and has the default initial capacity.
     */
    public OptimizedByteArrayOutputStream() {
        this(3 * 1024);
    }

    /**
     * Initializes a new instance of the <code>OptimizedByteArrayOutputStream</code> class 
     * that is empty and has the specified initial capacity.
     * 
     * @param capacity  The initial number of bytes that the 
     *                  <code>OptimizedByteArrayOutputStream</code> can contain.
     */
    public OptimizedByteArrayOutputStream(int capacity) {
        this.size = 0;
        this.buffer = new byte[capacity];
    }

    /**
     * Ensures that the buffer is large enought fro the given size.
     * 
     * @param size The given size.
     */
    private void resizeBuffer(int size) {
        if (size > buffer.length) {
            byte[] old;

            old = buffer;
            buffer = new byte[Math.max(size, buffer.length)];

            System.arraycopy(old, 0, buffer, 0, old.length);
            old = null;
        }
    }

    /**
     * Writes the specified byte to this output stream. The general contract for 
     * <code>write</code> is that one byte is written to the output stream. The byte to be 
     * written is the eight low-order bits of the argument <code>b</code>. The 24 high-order 
     * bits of <code>b</code> are ignored.
     * 
     * @param   b The byte.
     * @throws  IOException If an I/O error occurs. In particular, an <code>IOException</code> 
     *          may be thrown if the output stream has been closed.
     */
    @Override
    public final void write(int b) {
        resizeBuffer(size + 1);
        buffer[size++] = (byte)b;
    }

    /**
     * Writes <code>buffer.length</code> bytes from the specified byte array to this output 
     * stream. The general contract for <code>write(buffer)</code> is that it should have 
     * exactly the same effect as the call <code>write(buffer, 0, buffer.length)</code>.
     * 
     * @param   buffer The data.
     * @throws  IOException If an I/O error occurs.
     */
    @Override
    public final void write(byte[] buffer) {
        resizeBuffer(buffer.length);
        System.arraycopy(buffer, 0, this.buffer, size, buffer.length);
        size += buffer.length;
    }

    /**
     * Writes <code>length</code> bytes from the specified byte array starting at offset 
     * <code>offset</code> to this output stream. The general contract for 
     * <code>write(buffer, offset, length)</code> is that some of the bytes in the array 
     * <code>buffer</code> are written to the output stream in order; element 
     * <code>buffer[offset]</code> is the first byte written and 
     * <code>buffer[offset + length - 1]</code> is the last byte written by this operation.
     * <p>
     * The <code>write</code> method of <code>OptimizedByteArrayOutputStream</code> calls the 
     * write method of one argument on each of the bytes to be written out. Subclasses are 
     * encouraged to override this method and provide a more efficient implementation.
     * <p>
     * If <code>buffer</code> is null, a <code>NullPointerException</code> is thrown.
     * <p>
     * If <code>offset</code> is negative, or <code>length</code> is negative, or 
     * <code>offset + length</code> is greater than the length of the array 
     * <code>buffer</code>, then an <code>IndexOutOfBoundsException</code> is thrown.
     * 
     * @param   buffer  The data.
     * @param   offset  The start offset in the data.
     * @param   length  The number of bytes to write.
     * @throws  IOException If an I/O error occurs. In particular, an <code>IOException</code> 
     *          is thrown if the output stream is closed.
     */
    @Override
    public final void write(byte[] buffer, int offset, int length) {
        if (buffer == null) {
            throw new NullPointerException();
        }
        if ((offset < 0) || (offset > buffer.length) || (length < 0) || ((offset + length) > buffer.length) || ((offset + length) < 0)) {
            throw new IndexOutOfBoundsException();
        }
        if (length == 0) {
            return;
        }

        resizeBuffer(size + length);
        System.arraycopy(buffer, offset, this.buffer, size, length);
        size += length;
    }
    
    /**
     * Resets the <code>OptimizedByteArrayOutputStream</code>.
     */
    public void reset() {
        size = 0;
    }

    /**
     * Gets a <code>OptimizedByteArrayInputStream</code> for reading back the written data.
     * 
     * @return A <code>OptimizedByteArrayInputStream</code> for reading back the written data.
     */
    public InputStream inputStream() {
        return new OptimizedByteArrayInputStream(buffer, size);
    }
}

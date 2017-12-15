package edu.interpreter.model.utilities;

import java.io.InputStream;

/**
 * Implementation of <code>OptimizedByteArrayInputStream</code> that doesn't synchronize methods and 
 * doesn't copy the data on toByteArray().
 * 
 * @author David Perisanu
 * @author javatechniques.com
 */
public class OptimizedByteArrayInputStream extends InputStream {
    protected byte[] buffer;    // Byte buffer.
    protected int count;        // Number of bytes that can be read from the buffer.
    protected int position;     // Number of bytes that have been read from the buffer.

    /**
     * Initializes a new instance of the <code>OptimizedByteArrayInputStream</code> class 
     * with the given values.
     * 
     * @param buffer  The byte buffer.
     * @param count   The number of bytes that can be read from the buffer.
     */
    public OptimizedByteArrayInputStream(byte[] buffer, int count) {
        this.buffer = buffer;
        this.count = count;
        position = 0;
    }

    /**
     * Reads the next byte of data from the input stream. The value byte is returned as an 
     * <code>int</code> in the range <code>0</code> to <code>255</code>. If no byte is 
     * available because the end of the stream has been reached, the value <code>-1</code> 
     * is returned. This method blocks until input data is available, the end of the stream 
     * is detected, or an exception is thrown.
     * 
     * @return The next byte of data, or <code>-1</code> if the end of the stream is reached.
     */
    @Override
    public final int read() {
        return position < count ? buffer[position++] & 0xff : -1;
    }

    /**
     * Reads up to <code>length</code> bytes of data from the input stream into an array of 
     * bytes. An attempt is made to read as many as <code>length</code> bytes, but a smaller 
     * number may be read. The number of bytes actually read is returned as an integer.
     * <p>
     * If <code>length</code> is zero, then no bytes are read and <code>0</code> is returned; 
     * otherwise, there is an attempt to read at least one byte. If no byte is available 
     * because the stream is at end of file, the value <code>-1</code> is returned; otherwise, 
     * at least one byte is read and stored into <code>buffer</code>.
     * <p>
     * The first byte read is stored into element <code>buffer[offset]</code>, the next one 
     * into <code>buffer[offset + 1]</code>, and so on. The number of bytes read is, at most, 
     * equal to <code>length</code>. Let <code>k</code> be the number of bytes actually read; 
     * these bytes will be stored in elements <code>buffer[offset]</code> through 
     * <code>buffer[offset + k - 1]</code>, leaving elements <code>buffer[offset + k]</code> 
     * through <code>buffer[offset + length - 1]</code> unaffected.
     * <p>
     * In every case, elements <code>buffer[0]</code> through 
     * <code>buffer[offset]</code> and elements <code>buffer[offset + length]</code>  
     * through <code>buffer[buffer.length - 1]</code> are unaffected.
     *
     * @param   buffer  The buffer into which the data is read.
     * @param   offset  The start offset in array <code>b</code> at which the data is 
     *          written.
     * @param   length  The maximum number of bytes to read.
     * @return  The total number of bytes read into the buffer, or <code>-1</code> if 
     *          there is no more data because the end of the stream has been reached.
     * @throws  NullPointerException If <code>buffer</code> is <code>null</code>.
     * @throws  IndexOutOfBoundsException If <code>offset</code> is negative, 
     *          <code>length</code> is negative, or <code>length</code> is greater  than 
     *          <code>buffer.length - offset</code>.
     */
    @Override
    public final int read(byte[] buffer, int offset, int length) {
        if (buffer == null) {
            throw new NullPointerException();
        }
        if (offset < 0 || length < 0 || length > buffer.length - offset) {
            throw new IndexOutOfBoundsException();
        }
        
        if (length == 0) {
            return 0;
        }
        if (position >= count)
            return -1;

        if (position + length > count)
            length = count - position;

        System.arraycopy(this.buffer, position, buffer, offset, length);
        position += length;

        return length;
    }

    /**
     * Skips over and discards <code>n</code> bytes of data from this input stream. 
     * The <code>skip</code> method may, for a variety of reasons, end up skipping over some 
     * smaller number of bytes, possibly <code>0</code>. This may result from any of a number 
     * of conditions; reaching end of file before <code>n</code> bytes have been skipped is 
     * only one possibility.The actual number of bytes skipped is returned. If <code>n</code>
     * is negative, the <code>skip</code> method for class 
     * <code>OptimizedByteArrayInputStream</code> always returns <code>0</code>, and no bytes 
     * are skipped.
     *
     * @param   n The number of bytes to be skipped.
     * @return  The actual number of bytes skipped.
     */
    @Override
    public final long skip(long n) {
        if (n < 0)
            return 0;

        if (position + n > count)
            n = count - position;
        
        position += n;

        return n;
    }

    /**
     * Returns an estimate of the number of bytes that can be read (or skipped over) from 
     * this input stream without blocking by the next invocation of a method for this input 
     * stream.
     *
     * @return  An estimate of the number of bytes that can be read (or skipped over) from 
     *          this input stream without blocking or <code>0</code> when it reaches the 
     *          end of the input stream.
     */
    @Override
    public final int available() {
        return count - position;
    }
}

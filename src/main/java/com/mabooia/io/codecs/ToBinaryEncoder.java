package com.mabooia.io.codecs;

import com.mabooia.ShouldNotReachThisPointException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public interface ToBinaryEncoder<SOURCE> extends Encoder<SOURCE, byte[]> {

    @Override
    default byte[] encode(final SOURCE source) {
        try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            encodeTo(source, outputStream);
            return outputStream.toByteArray();
        }
        catch (IOException ex) {
            throw new ShouldNotReachThisPointException(ex);
        }
    }

    void encodeTo(final SOURCE source, final OutputStream outputStream) throws IOException;
}

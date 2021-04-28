package com.mabooia.io.codecs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface BinaryDecoder extends FromBinaryDecoder<byte[]> {

    @Override
    default byte[] decode(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        decode(inputStream, outputStream);

        return outputStream.toByteArray();
    }

    void decode(final InputStream inputStream, final OutputStream outputStream) throws IOException;
}

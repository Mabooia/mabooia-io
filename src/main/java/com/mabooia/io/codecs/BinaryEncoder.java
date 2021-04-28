package com.mabooia.io.codecs;

import static com.mabooia.io.IOUtils.toInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface BinaryEncoder extends ToBinaryEncoder<byte[]> {

    @Override
    default void encodeTo(final byte[] source, final OutputStream outputStream) throws IOException {
        encode(toInputStream(source), outputStream);
    }

    void encode(final InputStream inputStream, final OutputStream outputStream) throws IOException;
}

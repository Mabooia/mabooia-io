package com.mabooia.io;

import com.mabooia.ThrowingBiConsumer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class IOUtils {

    public static <E extends Exception> void readAllBytesWithBuffer(
        final InputStream inputStream,
        final ThrowingBiConsumer<byte[], Integer, E> bufferedReadConsumer) throws IOException, E {

        final byte[] buffer = new byte[4096];
        int read;
        while (0 < (read = inputStream.read(buffer))) {
            bufferedReadConsumer.accept(buffer, read);
        }
    }

    public static ByteArrayInputStream toInputStream(final byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    private IOUtils() {
    }
}

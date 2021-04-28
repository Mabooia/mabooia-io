package com.mabooia.io;

import static com.mabooia.io.IOUtils.toInputStream;

import com.mabooia.Try;
import com.mabooia.Unit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

@FunctionalInterface
public interface Writer {

    OutputStream openWriteStream(final String resource) throws IOException;

    default Try<Unit> writeAll(final String resource, final InputStream inputStream) {
        return Try.of(() -> {
            try (final OutputStream outputStream = openWriteStream(resource)) {
                IOUtils.copyLarge(inputStream, outputStream);
            }
        });
    }

    default Try<Unit> writeAllBytes(final String resource, final byte[] bytes) {
        return writeAll(resource, toInputStream(bytes));
    }

    default Try<Unit> writeAllText(final String resource, final String text, final Charset charset) {
        return writeAllBytes(resource, text.getBytes(charset));
    }
}

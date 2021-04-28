package com.mabooia.io;

import com.mabooia.Try;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;

@FunctionalInterface
public interface Reader {

    InputStream openReadStream(final String resource) throws IOException;

    default Try<byte[]> readAllBytes(final String resource) {
        return Try.of(() -> {
            try (final InputStream inputStream = openReadStream(resource)) {
                return IOUtils.toByteArray(inputStream);
            }
        });
    }

    default Try<String> readAllText(final String resource, Charset charset) {
        return readAllBytes(resource)
            .map(bytes -> new String(bytes, charset));
    }
}

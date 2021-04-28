package com.mabooia.io.codecs;

import java.io.IOException;
import java.io.OutputStream;

@FunctionalInterface
public interface TextToBinaryDecoder extends ToBinaryDecoder<String> {

    default void decodeTo(final String src, final OutputStream outputStream) throws IOException { {
        outputStream.write(decode(src));
    }}
}

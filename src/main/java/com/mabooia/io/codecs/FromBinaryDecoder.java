package com.mabooia.io.codecs;

import static com.mabooia.io.IOUtils.toInputStream;

import com.mabooia.ShouldNotReachThisPointException;
import com.mabooia.Try;
import java.io.IOException;
import java.io.InputStream;

public interface FromBinaryDecoder<TARGET> extends Decoder<byte[], TARGET> {

    @Override
    default TARGET decode(final byte[] bytes) {
        return Try
            .of(() -> decode(toInputStream(bytes)))
            .toOptional()
            .orElseThrow(ShouldNotReachThisPointException::new);
    }

    TARGET decode(final InputStream inputStream) throws IOException;
}

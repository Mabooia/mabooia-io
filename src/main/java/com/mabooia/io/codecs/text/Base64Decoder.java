package com.mabooia.io.codecs.text;

import com.mabooia.io.codecs.TextToBinaryDecoder;
import java.util.Base64;

public enum Base64Decoder implements TextToBinaryDecoder {
    STANDARD(Base64.getDecoder()),
    MIME(Base64.getMimeDecoder()),
    URL(Base64.getUrlDecoder());

    private final Base64.Decoder innerDecoder;

    Base64Decoder(Base64.Decoder innerDecoder) {
        this.innerDecoder = innerDecoder;
    }

    @Override
    public byte[] decode(final String src) {
        return innerDecoder.decode(src);
    }
}

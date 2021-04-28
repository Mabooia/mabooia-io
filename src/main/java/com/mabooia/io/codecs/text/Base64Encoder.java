package com.mabooia.io.codecs.text;

import com.mabooia.io.codecs.BinaryToTextEncoder;
import java.util.Base64;

public enum Base64Encoder implements BinaryToTextEncoder {
    STANDARD(Base64.getEncoder()),
    STANDARD_NO_PADDING(Base64.getEncoder().withoutPadding()),
    MIME(Base64.getMimeEncoder()),
    MIME_NO_PADDING(Base64.getMimeEncoder().withoutPadding()),
    URL(Base64.getUrlEncoder()),
    URL_NO_PADDING(Base64.getUrlEncoder().withoutPadding());

    private final Base64.Encoder innerEncoder;

    Base64Encoder(Base64.Encoder innerEncoder) {
        this.innerEncoder = innerEncoder;
    }

    @Override
    public String encode(byte[] source) {
        return innerEncoder.encodeToString(source);
    }
}

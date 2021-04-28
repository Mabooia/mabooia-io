package com.mabooia.io.codecs;

@FunctionalInterface
public interface Decoder<SOURCE, TARGET> {
    TARGET decode(final SOURCE src);
}

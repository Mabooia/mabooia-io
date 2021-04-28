package com.mabooia.io.codecs;

@FunctionalInterface
public interface Encoder<SOURCE, TARGET> {
    TARGET encode(final SOURCE source);
}

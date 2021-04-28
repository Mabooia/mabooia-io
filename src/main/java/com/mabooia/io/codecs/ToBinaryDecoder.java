package com.mabooia.io.codecs;

@FunctionalInterface
public interface ToBinaryDecoder<SOURCE> extends Decoder<SOURCE, byte[]> {
}

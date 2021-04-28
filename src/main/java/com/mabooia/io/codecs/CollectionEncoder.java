package com.mabooia.io.codecs;

import com.mabooia.collections.streams.Stream;

public interface CollectionEncoder<SOURCE, TARGET> extends Encoder<Stream<? extends SOURCE>, TARGET> {
}

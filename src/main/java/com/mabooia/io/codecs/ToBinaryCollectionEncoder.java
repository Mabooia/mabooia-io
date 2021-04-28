package com.mabooia.io.codecs;

import com.mabooia.collections.streams.Stream;

public interface ToBinaryCollectionEncoder<SOURCE>
    extends
    CollectionEncoder<SOURCE, byte[]>,
    ToBinaryEncoder<Stream<? extends SOURCE>> {
}

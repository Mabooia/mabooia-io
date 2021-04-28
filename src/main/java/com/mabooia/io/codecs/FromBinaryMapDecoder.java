package com.mabooia.io.codecs;

import java.util.Map;

public interface FromBinaryMapDecoder<KEY, VALUE>
    extends
        MapDecoder<byte[], KEY, VALUE>,
        FromBinaryDecoder<Map<KEY, VALUE>> {
}

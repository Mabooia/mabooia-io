package com.mabooia.io.codecs;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public abstract class BinaryEntriesMapDecoder<KEY> implements FromBinaryMapDecoder<KEY, byte[]> {

    @Override
    public Map<KEY, byte[]> decode(final InputStream inputStream) throws IOException {

        final List<Pair<KEY, byte[]>> entries = new LinkedList<>();

        final InputStream entryInputStream = getNextEntryStream(inputStream);
        KEY key;
        while (null != (key = getNextEntry(entryInputStream))) {
            final byte[] bytes = IOUtils.toByteArray(entryInputStream);
            entries.add(new ImmutablePair<>(key, bytes));
        }

        return entries
            .stream()
            .collect(Collectors.toMap(Pair::getLeft, Pair::getRight));
    }

    protected abstract InputStream getNextEntryStream(final InputStream inputStream) throws IOException;

    protected abstract KEY getNextEntry(final InputStream inputStream) throws IOException;
}

package com.mabooia.io.codecs.zip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.mabooia.collections.streams.Streams;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.zip.ZipEntry;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class ZipEncoderDecoderTest {

    @Test
    public void testEncodingDecoding() {
        // given
        final Charset utf8 = StandardCharsets.UTF_8;
        final String entryKey = "/file-name.txt";
        final String entryContent = "Text file content!!";

        final ZipEncoder zipEncoder = ZipEncoder.builder()
            .build();

        final ZipDecoder zipDecoder = ZipDecoder.builder()
            .build();

        // when
        final byte[] compressed = zipEncoder.encode(
            Streams.singleton(Pair.of(entryKey, entryContent.getBytes(utf8)))
        );
        final Map<ZipEntry, byte[]> decompressed = zipDecoder.decode(compressed);

        // then
        assertSame(1, decompressed.size());
        assertEquals(entryKey, Streams.asStream(decompressed.keySet()).getHeadIfPresent().map(ZipEntry::getName).orElse(""));
        assertEquals(entryContent, new String(Streams.asStream(decompressed.values()).getHeadIfPresent().orElse(new byte[0]), utf8));
    }
}

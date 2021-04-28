package com.mabooia.io.codecs.zip;

import static com.mabooia.io.IOUtils.toInputStream;

import com.mabooia.Try;
import com.mabooia.io.codecs.ToBinaryCollectionEncoder;
import com.mabooia.collections.streams.Stream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Optional;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import lombok.Builder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;

public class ZipEncoder implements ToBinaryCollectionEncoder<Map.Entry<String, InputStream>> {

    private final int deflateLevel;
    private final int method;

    @Builder
    private ZipEncoder(final Integer deflateLevel, final Integer method) {
        this.deflateLevel = Optional.ofNullable(deflateLevel).orElse(Deflater.DEFAULT_COMPRESSION);
        this.method = Optional.ofNullable(method).orElse(Deflater.DEFLATED);
    }

    @Override
    public void encodeTo(final Stream<? extends Map.Entry<String, InputStream>> entries, final OutputStream outputStream)
        throws IOException {

        final ZipOutputStream zipOutputStream = getOutputStreamTo(outputStream);
        entries.forEachEx(entry -> encodeEntry(entry.getKey(), entry.getValue(), zipOutputStream));
        zipOutputStream.flush();
    }

    public byte[] encode(final Stream<? extends Map.Entry<String, byte[]>> entries) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Try.of(() -> {
            final Stream<Map.Entry<String, InputStream>> mappedEntries = entries
                .map(entry -> Pair.of(entry.getKey(), toInputStream(entry.getValue())));

            encodeTo(mappedEntries, byteArrayOutputStream);
        });

        return byteArrayOutputStream.toByteArray();
    }

    public void encodeEntry(
        final String key,
        final InputStream entryContent,
        final ZipOutputStream zipOutputStream) throws IOException {

        zipOutputStream.putNextEntry(new ZipEntry(key));
        IOUtils.copyLarge(entryContent, zipOutputStream);
        zipOutputStream.closeEntry();
    }

    public void encodeEntry(
        final String key,
        final byte[] entryContent,
        final ZipOutputStream zipOutputStream) throws IOException {

        encodeEntry(key, toInputStream(entryContent), zipOutputStream);
    }

    public void encodeEntry(
        final Map.Entry<String, byte[]> entry,
        final ZipOutputStream zipOutputStream) throws IOException {

        encodeEntry(entry.getKey(), entry.getValue(), zipOutputStream);
    }

    public ZipOutputStream getOutputStreamTo(final OutputStream outputStream) {
        final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        zipOutputStream.setLevel(deflateLevel);
        zipOutputStream.setMethod(method);

        return zipOutputStream;
    }
}

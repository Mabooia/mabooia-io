package com.mabooia.io.codecs.zip;

import com.mabooia.io.codecs.BinaryEntriesMapDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lombok.Builder;

@Builder
public class ZipDecoder extends BinaryEntriesMapDecoder<ZipEntry> {

    @Override
    protected InputStream getNextEntryStream(InputStream inputStream) {
        return new ZipInputStream(inputStream);
    }

    @Override
    protected ZipEntry getNextEntry(InputStream inputStream) throws IOException {
        return ((ZipInputStream) inputStream).getNextEntry();
    }
}

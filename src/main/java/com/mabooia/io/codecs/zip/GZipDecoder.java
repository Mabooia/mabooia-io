package com.mabooia.io.codecs.zip;

import com.mabooia.io.codecs.BinaryDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import org.apache.commons.io.IOUtils;

public class GZipDecoder implements BinaryDecoder {

    @Override
    public void decode(InputStream inputStream, OutputStream outputStream) throws IOException {
        final GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
        IOUtils.copy(gzipInputStream, outputStream);
    }
}

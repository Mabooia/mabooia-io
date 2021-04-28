package com.mabooia.io.codecs.zip;

import com.mabooia.io.codecs.BinaryEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.io.IOUtils;

public class GZipEncoder implements BinaryEncoder {

    @Override
    public void encode(InputStream inputStream, OutputStream outputStream) throws IOException {
        final GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
        IOUtils.copy(inputStream, gzipOutputStream);
        gzipOutputStream.finish();
    }
}

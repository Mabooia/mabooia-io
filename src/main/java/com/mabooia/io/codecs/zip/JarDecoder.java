package com.mabooia.io.codecs.zip;

import com.mabooia.io.codecs.BinaryEntriesMapDecoder;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

public class JarDecoder extends BinaryEntriesMapDecoder<JarEntry> {

    @Override
    protected InputStream getNextEntryStream(InputStream inputStream) throws IOException {
        return new JarInputStream(inputStream);
    }

    @Override
    protected JarEntry getNextEntry(InputStream inputStream) throws IOException {
        return ((JarInputStream)inputStream).getNextJarEntry();
    }
}

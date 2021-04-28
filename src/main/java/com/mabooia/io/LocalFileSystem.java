package com.mabooia.io;

import com.mabooia.Try;
import com.mabooia.collections.streams.Streams;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class LocalFileSystem {

    public static Reader getReader() {
        return FileInputStream::new;
    }

    public static Writer getWriter() {
        return FileOutputStream::new;
    }

    public static ObjectLister<File> getDirectoryLister(final String path) {
        return new ObjectLister<>() {
            @Override
            public ResourceStream<File> listObjects() {
                final File file = new File(path);
                if (!file.exists() || !file.isDirectory()) {
                    return ResourceStream.empty();
                }

                return Try.of(() -> {
                        final DirectoryStream<Path> dirStream = Files.newDirectoryStream(Paths.get(path));
                        return ResourceStream.of(
                            Streams.asStream(dirStream).map(Path::toFile),
                            dirStream::close
                        );
                    })
                    .toOptional()
                    .orElseGet(ResourceStream::empty);
            }

            @Override
            public ResourceStream<String> listObjectNames() {
                return listObjects()
                    .map(File::getAbsolutePath);
            }
        };
    }

    private LocalFileSystem() {
    }
}

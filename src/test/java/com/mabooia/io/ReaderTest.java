package com.mabooia.io;

import static com.mabooia.io.IOUtils.toInputStream;
import static org.junit.Assert.assertArrayEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mabooia.ThrowingFunction;
import com.mabooia.Try;
import com.mabooia.TryTest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import org.junit.Test;

public class ReaderTest {

    @Test
    public void testSuccessfullyReadAllBytes() {
        // given
        final byte[] resourceContent = "hello world".getBytes(StandardCharsets.UTF_8);

        @SuppressWarnings("unchecked")
        final Function<String, ByteArrayInputStream> openInputStream = mock(Function.class);
        when(openInputStream.apply(any())).thenReturn(toInputStream(resourceContent));

        final Reader reader = openInputStream::apply;

        // when
        final Try<byte[]> res = reader.readAllBytes(anyString());

        // then
        TryTest.assertSuccess(res);
        res.ifSuccess(value -> assertArrayEquals(resourceContent, value));
        verify(openInputStream, times(1)).apply(any());
    }

    @Test
    public void testFailedReadAllBytes() throws IOException {
        // given
        final IOException ex = new IOException();

        @SuppressWarnings("unchecked")
        final ThrowingFunction<String, ByteArrayInputStream, IOException> openInputStream = mock(ThrowingFunction.class);
        doThrow(ex).when(openInputStream).apply(anyString());

        final Reader reader = openInputStream::apply;

        // when
        final Try<byte[]> res = reader.readAllBytes(anyString());

        // then
        TryTest.assertFailure(res, ex);
        verify(openInputStream, times(1)).apply(any());
    }

    @Test
    public void testSuccessfullyReadAllText() {
        // given
        final Charset utf8 = StandardCharsets.UTF_8;
        final String content = "Hello World";

        @SuppressWarnings("unchecked")
        final Function<String, ByteArrayInputStream> openReadStream = mock(Function.class);
        when(openReadStream.apply(any())).thenReturn(toInputStream(content.getBytes(utf8)));

        final Reader reader = openReadStream::apply;

        // when
        final Try<String> res = reader.readAllText(anyString(), utf8);

        // then
        TryTest.assertSuccess(res, content);
        verify(openReadStream, times(1)).apply(any());
    }
}

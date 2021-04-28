package com.mabooia.io;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.mabooia.ThrowingFunction;
import com.mabooia.Try;
import com.mabooia.TryTest;
import com.mabooia.Unit;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import org.junit.Test;

public class WriterTest {

    @Test
    public void testSuccessfullyWriteAllBytes() {
        // given
        final Charset utf8 = StandardCharsets.UTF_8;
        final String content = "Any Content";
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        @SuppressWarnings("unchecked")
        final Function<String, ByteArrayOutputStream> openWriteStream = mock(Function.class);
        when(openWriteStream.apply(any())).thenReturn(byteArrayOutputStream);

        final Writer writer = openWriteStream::apply;

        // when
        final Try<Unit> res = writer.writeAllBytes(anyString(), content.getBytes(utf8));

        // then
        TryTest.assertSuccess(res);
        assertEquals(content, byteArrayOutputStream.toString(utf8));
    }

    @Test
    public void testFailedWriteAllBytes() throws IOException {
        // given
        final Charset utf8 = StandardCharsets.UTF_8;
        final String content = "Any Content";
        final IOException ex = new IOException();

        @SuppressWarnings("unchecked")
        final ThrowingFunction<String, ByteArrayOutputStream, IOException> openWriteStream = mock(ThrowingFunction.class);
        doThrow(ex).when(openWriteStream).apply(any());

        final Writer writer = openWriteStream::apply;

        // when
        final Try<Unit> res = writer.writeAllBytes(anyString(), content.getBytes(utf8));

        // then
        TryTest.assertFailure(res, ex);
    }

    @Test
    public void testSuccessfullyWriteAllText() {
        // given
        final Charset utf8 = StandardCharsets.UTF_8;
        final String content = "Any Content";
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        @SuppressWarnings("unchecked")
        final Function<String, ByteArrayOutputStream> openWriteStream = mock(Function.class);
        when(openWriteStream.apply(any())).thenReturn(byteArrayOutputStream);

        final Writer writer = openWriteStream::apply;

        // when
        final Try<Unit> res = writer.writeAllText(anyString(), content, utf8);

        // then
        TryTest.assertSuccess(res);
        assertEquals(content, byteArrayOutputStream.toString(utf8));
    }
}

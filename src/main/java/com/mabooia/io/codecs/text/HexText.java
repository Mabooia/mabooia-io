package com.mabooia.io.codecs.text;

import com.mabooia.ShouldNotReachThisPointException;
import com.mabooia.io.codecs.BinaryToTextEncoder;
import com.mabooia.io.codecs.TextToBinaryDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public final class HexText {

    private HexText() { }

    public static BinaryToTextEncoder getEncoder() {
        return new HexEncoder();
    }
    public static TextToBinaryDecoder getDecoder() {
        return new HexDecoder();
    }

    public static boolean isHex(final String str) {
        return str != null
            && str.length() % 2 == 0
            && str
                .chars()
                .allMatch(ch ->
                    ('0' <= ch && '9' >= ch)
                    || ('a' <= ch && 'f' >= ch)
                    || ('A' <= ch && 'F' >= ch));
    }

    private static final String alphabet = "0123456789abcdef";

    private static final class HexEncoder implements BinaryToTextEncoder {

        @Override
        public String encode(byte[] bytes) {
            final StringBuilder builder = new StringBuilder();
            for (final byte b : bytes) {
                final char hiChar = alphabet.charAt((b & 0xf0) >> 0x04);
                final char lowChar = alphabet.charAt(b & 0x0f);

                builder.append(hiChar).append(lowChar);
            }

            return builder.toString();
        }
    }

    private static final class HexDecoder implements TextToBinaryDecoder {

        @Override
        public byte[] decode(final String str) {
            try (final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
                decodeTo(str, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ignored) {
                throw new ShouldNotReachThisPointException();
            }
        }

        @Override
        public void decodeTo(final String str, final OutputStream outputStream) throws IOException {

            final byte[] bytes = new byte[str.length()/2];

            final String lowerCased = str.toLowerCase();
            final int length = lowerCased.length();
            for (int i = 0; i < length; i+=2) {

                final char hiChar = lowerCased.charAt(i);
                final char lowChar = lowerCased.charAt(i + 1);
                final int hiIndex = alphabet.indexOf(hiChar);
                final int lowIndex = alphabet.indexOf(lowChar);

                final byte hi = hiIndex >= 0 ? (byte)(hiIndex << 0x04) : Byte.MIN_VALUE;
                final byte low = hiIndex >= 0 ? (byte)lowIndex : Byte.MIN_VALUE;

                bytes[i / 2] = (byte)(hi | low);
            }

            outputStream.write(bytes);
        }
    }
}

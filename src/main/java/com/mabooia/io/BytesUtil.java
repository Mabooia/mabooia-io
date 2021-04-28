package com.mabooia.io;

import com.mabooia.Try;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;

public final class BytesUtil {

    public static byte[] join(final byte separator, final byte[]...byteArrays) {
        return join(new byte[] { separator }, byteArrays);
    }

    public static byte[] join(final byte[] separator, final byte[]...byteArrays) {

        final var byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i < byteArrays.length; i++) {
            final int idx = i;
            final byte[] bytes = byteArrays[i];
            Try.of(() -> {
                if (idx > 0) {
                    byteArrayOutputStream.write(separator);
                }
                byteArrayOutputStream.write(bytes);
            });
        }

        return byteArrayOutputStream.toByteArray();
    }

    public static int indexOf(final byte b, final byte[] bytes) {
        return indexOf(b, bytes, 0);
    }

    public static int indexOf(final byte b, final byte[] bytes, int offset) {
        int idx = -1;
        for (int i = offset; i < bytes.length; i++) {
            if (bytes[i] == b) {
                idx = i;
                break;
            }
        }

        return idx;
    }

    public static byte[] toByteArray(short value) {
        return ByteBuffer.allocate(Short.BYTES).putShort(value).array();
    }

    public static byte[] toByteArray(int value) {
        return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
    }

    public static byte[] toByteArray(long value) {
        return ByteBuffer.allocate(Long.BYTES).putLong(value).array();
    }

    public static byte[] toByteArray(float value) {
        return ByteBuffer.allocate(Float.BYTES).putFloat(value).array();
    }

    public static byte[] toByteArray(double value) {
        return ByteBuffer.allocate(Double.BYTES).putDouble(value).array();
    }

    public static byte[] toByteArray(BigInteger value) {
        return value.toByteArray();
    }

    public static short asShort(byte[] bytes, int index) {
        return ByteBuffer.wrap(bytes, index, Short.BYTES).asShortBuffer().get();
    }

    public static short asShort(byte[] bytes) {
        return asShort(bytes, 0);
    }

    public static int asInteger(byte[] bytes, int index) {
        return ByteBuffer.wrap(bytes, index, Integer.BYTES).asIntBuffer().get();
    }

    public static int asInteger(byte[] bytes) {
        return asInteger(bytes, 0);
    }

    public static long asLong(byte[] bytes, int index) {
        return ByteBuffer.wrap(bytes, index, Long.BYTES).asLongBuffer().get();
    }

    public static long asLong(byte[] bytes) {
        return asLong(bytes, 0);
    }

    public static float asFloat(byte[] bytes, int index) {
        return ByteBuffer.wrap(bytes, index, Float.BYTES).asFloatBuffer().get();
    }

    public static float asFloat(byte[] bytes) {
        return asFloat(bytes, 0);
    }

    public static double asDouble(byte[] bytes, int index) {
        return ByteBuffer.wrap(bytes, index, Double.BYTES).asDoubleBuffer().get();
    }

    public static double asDouble(byte[] bytes) {
        return asDouble(bytes, 0);
    }

    public static BigInteger asBigInteger(byte[] bytes) {
        return new BigInteger(bytes);
    }

    private BytesUtil() {
    }
}

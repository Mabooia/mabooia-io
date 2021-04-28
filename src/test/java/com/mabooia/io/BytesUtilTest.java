package com.mabooia.io;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class BytesUtilTest {

    @Test
    public void testToByteArrayShort() {
        // given
        final short number = 16978;

        // when
        final byte[] bytes = BytesUtil.toByteArray(number);

        // then
        assertArrayEquals(new byte[]{(byte)(number >> 8), (byte)(number << 8 >> 8)}, bytes);
    }
}

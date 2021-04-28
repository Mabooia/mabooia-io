package com.mabooia.io.codecs.text;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class HexTextTest {

    @Test
    public void testIsHexShouldReturnTrue() {
        // given
        final String str = "0015a48cefff";

        // then
        assertTrue(
            String.format("Text '%s' should be a valid hex", str),
            HexText.isHex(str)
        );
    }

    @Test
    public void testIsHexWithOddLengthShouldReturnFalse() {
        // given
        final String str = "0015a48ceff";

        // then
        assertFalse(
            String.format("Text '%s' should be an invalid hex", str),
            HexText.isHex(str)
        );
    }

    @Test
    public void testIsHexWithNotValidCharsShouldReturnFalse() {
        // given
        final String str = "0015a48jcefff";

        // then
        assertFalse(
            String.format("Text '%s' should be an invalid hex", str),
            HexText.isHex(str)
        );
    }

    @Test
    public void testEncoding() {
        // given
        final byte[] data = new byte[] {
            (byte) 0x00,
            (byte) 0x15,
            (byte) 0xa4,
            (byte) 0x8c,
            (byte) 0xef,
            (byte) 0xff
        };

        // when
        final String hex = HexText.getEncoder().encode(data);

        // then
        assertEquals("Wrong hex encoding", "0015a48cefff", hex);
    }

    @Test
    public void testDecoding() {
        // given
        final String hex = "0015a48cefff";

        // when
        final byte[] data = HexText.getDecoder().decode(hex);

        // then
        assertArrayEquals(
            "Wrong hex decoding",
            new byte[] {
                (byte) 0x00,
                (byte) 0x15,
                (byte) 0xa4,
                (byte) 0x8c,
                (byte) 0xef,
                (byte) 0xff
            },
            data);
    }
}

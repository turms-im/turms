package org.bouncycastle.util;

/**
 * Utility methods for converting byte arrays into ints and longs, and back again.
 */
public abstract class Pack {

    public static int bigEndianToInt(byte[] bs, int off) {
        int n = bs[off] << 24;
        n |= (bs[++off] & 0xff) << 16;
        n |= (bs[++off] & 0xff) << 8;
        n |= (bs[++off] & 0xff);
        return n;
    }

    public static void bigEndianToInt(byte[] bs, int off, int[] ns) {
        for (int i = 0; i < ns.length; ++i) {
            ns[i] = bigEndianToInt(bs, off);
            off += 4;
        }
    }

    public static void intToBigEndian(int n, byte[] bs, int off) {
        bs[off] = (byte) (n >>> 24);
        bs[++off] = (byte) (n >>> 16);
        bs[++off] = (byte) (n >>> 8);
        bs[++off] = (byte) (n);
    }

    public static void intToBigEndian(int[] ns, byte[] bs, int off) {
        for (int i = 0; i < ns.length; ++i) {
            intToBigEndian(ns[i], bs, off);
            off += 4;
        }
    }

    /**
     * @param value The number
     * @param bs    The target.
     * @param off   Position in target to start.
     * @param bytes number of bytes to write.
     * @deprecated Will be removed
     */
    public static void longToBigEndian(long value, byte[] bs, int off, int bytes) {
        for (int i = bytes - 1; i >= 0; i--) {
            bs[i + off] = (byte) (value & 0xff);
            value >>>= 8;
        }
    }

}
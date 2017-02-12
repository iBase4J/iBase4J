package org.ibase4j.core.support.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;

/**
 * 密码器类
 * 
 * @author ShenHuaJie
 * @since 2011-12-31
 */
public class BASE64Encoder {

    /**
     * 译码数据源
     */
    private static final char[] PEM_ARRAY = {
        // 0  1   2   3    4    5    6    7
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', // 0
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', // 1
        'q', 'r', 's', 't', 'u', 'v', 'w', 'x', // 2
        'y', 'z', '1', '2', '3', '4', '5', '6', // 3
        '7', '8', '9', '0', 'A', 'B', 'C', 'D', // 4
        'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', // 5
        'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', // 6
        'U', 'V', 'W', 'X', 'Y', 'Z', '+', '/' // 7
    };

    private static final byte[] pem_convert_array = new byte[256];

    private byte[] decode_buffer = new byte[4];

    public BASE64Encoder() {
    }

    /**
     * 编码
     */
    public String encode(byte[] bt) {
        int totalBits = bt.length * 8;
        int nn = totalBits % 6;
        int curPos = 0;// process bits
        StringBuilder toReturn = new StringBuilder(32);
        while (curPos < totalBits) {
            int bytePos = curPos / 8;
            switch (curPos % 8) {
            case 0:
                toReturn.append(PEM_ARRAY[(bt[bytePos] & 0xfc) >> 2]);
                break;
            case 2:
                toReturn.append(PEM_ARRAY[(bt[bytePos] & 0x3f)]);
                break;
            case 4:
                if (bytePos == bt.length - 1) {
                    toReturn.append(PEM_ARRAY[((bt[bytePos] & 0x0f) << 2) & 0x3f]);
                } else {
                    int pos = (((bt[bytePos] & 0x0f) << 2) | ((bt[bytePos + 1] & 0xc0) >> 6)) & 0x3f;
                    toReturn.append(PEM_ARRAY[pos]);
                }
                break;
            case 6:
                if (bytePos == bt.length - 1) {
                    toReturn.append(PEM_ARRAY[((bt[bytePos] & 0x03) << 4) & 0x3f]);
                } else {
                    int pos = (((bt[bytePos] & 0x03) << 4) | ((bt[bytePos + 1] & 0xf0) >> 4)) & 0x3f;
                    toReturn.append(PEM_ARRAY[pos]);
                }
                break;
            default:
                break;
            }
            curPos += 6;
        }
        if (nn == 2) {
            toReturn.append("==");
        } else if (nn == 4) {
            toReturn.append("=");
        }
        return toReturn.toString();
    }

    /**
     * 解码
     */
    public byte[] decode(String str) throws IOException {
        byte[] arrayOfByte = str.getBytes();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(arrayOfByte);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        decodeBuffer(inputStream, outputStream);
        return outputStream.toByteArray();
    }

    private void decodeBuffer(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
        PushbackInputStream localPushbackInputStream = new PushbackInputStream(paramInputStream);
        int j = 0;
        while (true) {
            try {
                int k = bytesPerLine();
                int i = 0;
                if (i + bytesPerAtom() < k) {
                    decodeAtom(localPushbackInputStream, paramOutputStream, bytesPerAtom());
                    j += bytesPerAtom();
                    i += bytesPerAtom();
                    continue;
                }

                if (i + bytesPerAtom() == k) {
                    decodeAtom(localPushbackInputStream, paramOutputStream, bytesPerAtom());
                    j += bytesPerAtom();
                } else {
                    decodeAtom(localPushbackInputStream, paramOutputStream, k - i);
                    j += k - i;
                }
            } catch (RuntimeException e) {
                String.valueOf(j);
                break;
            }
        }
    }

    private int bytesPerAtom() {
        return 4;
    }

    private int bytesPerLine() {
        return 72;
    }

    private void decodeAtom(PushbackInputStream paramPushbackInputStream, OutputStream paramOutputStream, int paramInt)
        throws IOException {
        int i;
        int j = -1;
        int k = -1;
        int m = -1;
        int n = -1;

        if (paramInt < 2) {
            throw new java.lang.ArrayStoreException("BASE64Decoder: Not enough bytes for an atom.");
        }
        do {
            i = paramPushbackInputStream.read();
            if (i == -1) {
                throw new RuntimeException();
            }
        } while ((i == 10) || (i == 13));
        this.decode_buffer[0] = (byte)i;

        i = readFully(paramPushbackInputStream, this.decode_buffer, 1, paramInt - 1);
        if (i == -1) {
            throw new RuntimeException();
        }

        if ((paramInt > 3) && (this.decode_buffer[3] == 61)) {
            paramInt = 3;
        }
        if ((paramInt > 2) && (this.decode_buffer[2] == 61)) {
            paramInt = 2;
        }
        switch (paramInt) {
        case 4:
            n = pem_convert_array[(this.decode_buffer[3] & 0xFF)];
        case 3:
            m = pem_convert_array[(this.decode_buffer[2] & 0xFF)];
        case 2:
            k = pem_convert_array[(this.decode_buffer[1] & 0xFF)];
            j = pem_convert_array[(this.decode_buffer[0] & 0xFF)];
        }

        switch (paramInt) {
        case 2:
            paramOutputStream.write((byte)(j << 2 & 0xFC | k >>> 4 & 0x3));
            break;
        case 3:
            paramOutputStream.write((byte)(j << 2 & 0xFC | k >>> 4 & 0x3));
            paramOutputStream.write((byte)(k << 4 & 0xF0 | m >>> 2 & 0xF));
            break;
        case 4:
            paramOutputStream.write((byte)(j << 2 & 0xFC | k >>> 4 & 0x3));
            paramOutputStream.write((byte)(k << 4 & 0xF0 | m >>> 2 & 0xF));
            paramOutputStream.write((byte)(m << 6 & 0xC0 | n & 0x3F));
        }
    }

    private int readFully(InputStream paramInputStream, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException {
        for (int i = 0; i < paramInt2; i++) {
            int j = paramInputStream.read();
            if (j == -1) {
                return i == 0 ? -1 : i;
            }
            paramArrayOfByte[(i + paramInt1)] = (byte)j;
        }
        return paramInt2;
    }

    static {
        for (int i = 0; i < 255; i++) {
            pem_convert_array[i] = -1;
        }
        for (int i = 0; i < PEM_ARRAY.length; i++)
            pem_convert_array[PEM_ARRAY[i]] = (byte)i;
    }
}

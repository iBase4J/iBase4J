/* 2 * Copyright (c) 1995, 2004, Oracle and/or its affiliates. All rights reserved.
 * 3 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 * 4 *
 * 5 * This code is free software; you can redistribute it and/or modify it
 * 6 * under the terms of the GNU General Public License version 2 only, as
 * 7 * published by the Free Software Foundation. Oracle designates this
 * 8 * particular file as subject to the "Classpath" exception as provided
 * 9 * by Oracle in the LICENSE file that accompanied this code.
 * 10 *
 * 11 * This code is distributed in the hope that it will be useful, but WITHOUT
 * 12 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * 13 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * 14 * version 2 for more details (a copy is included in the LICENSE file that
 * 15 * accompanied this code).
 * 16 *
 * 17 * You should have received a copy of the GNU General Public License version
 * 18 * 2 along with this work; if not, write to the Free Software Foundation,
 * 19 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 20 *
 * 21 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * 22 * or visit www.oracle.com if you need additional information or have any
 * 23 * questions.
 * 24 */

package org.ibase4j.core.support.security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

/**
 * 37 * This class defines the decoding half of character encoders. 38 * A
 * character decoder is an algorithim for transforming 8 bit 39 * binary data
 * that has been encoded into text by a character 40 * encoder, back into
 * original binary form. 41 * 42 * The character encoders, in general, have been
 * structured 43 * around a central theme that binary data can be encoded into
 * 44 * text that has the form: 45 * 46 *
 * 
 * <pre>
 *    47    *      [Buffer Prefix]
 *    48    *      [Line Prefix][encoded data atoms][Line Suffix]
 *    49    *      [Buffer Suffix]
 *    50    *
 * </pre>
 * 
 * 51 * 52 * Of course in the simplest encoding schemes, the buffer has no 53 *
 * distinct prefix of suffix, however all have some fixed relationship 54 *
 * between the text in an 'atom' and the binary data itself. 55 * 56 * In the
 * CharacterEncoder and CharacterDecoder classes, one complete 57 * chunk of
 * data is referred to as a <i>buffer</i>. Encoded buffers 58 * are all text,
 * and decoded buffers (sometimes just referred to as 59 * buffers) are binary
 * octets. 60 * 61 * To create a custom decoder, you must, at a minimum, overide
 * three 62 * abstract methods in this class. 63 *
 * <DL>
 * 64 *
 * <DD>bytesPerAtom which tells the decoder how many bytes to 65 * expect from
 * decodeAtom 66 *
 * <DD>decodeAtom which decodes the bytes sent to it as text. 67 *
 * <DD>bytesPerLine which tells the encoder the maximum number of 68 * bytes per
 * line. 69 *
 * </DL>
 * 70 * 71 * In general, the character decoders return error in the form of a 72
 * * CEFormatException. The syntax of the detail string is 73 *
 * 
 * <pre>
 *    74    *      DecoderClassName: Error message.
 *    75    *
 * </pre>
 * 
 * 76 * 77 * Several useful decoders have already been written and are 78 *
 * referenced in the See Also list below. 79 * 80 * @author Chuck McManis 81 * @see
 * CEFormatException 82 * @see CharacterEncoder 83 * @see UCDecoder 84 * @see
 * UUDecoder 85 * @see BASE64Decoder 86
 */

public abstract class CharacterDecoder {

    /** Return the number of bytes per atom of decoding */
    abstract protected int bytesPerAtom();

    /** Return the maximum number of bytes that can be encoded per line */
    abstract protected int bytesPerLine();

    /** decode the beginning of the buffer, by default this is a NOP. */
    protected void decodeBufferPrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
    }

    /** decode the buffer suffix, again by default it is a NOP. */
    protected void decodeBufferSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
    }

    /**
     * 103 * This method should return, if it knows, the number of bytes 104 *
     * that will be decoded. Many formats such as uuencoding provide 105 * this
     * information. By default we return the maximum bytes that 106 * could have
     * been encoded on the line. 107
     */
    protected int decodeLinePrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
        return (bytesPerLine());
    }

    /**
     * 113 * This method post processes the line, if there are error detection
     * 114 * or correction codes in a line, they are generally processed by 115
     * * this method. The simplest version of this method looks for the 116 *
     * (newline) character. 117
     */
    protected void decodeLineSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
    }

    /**
     * 121 * This method does an actual decode. It takes the decoded bytes and
     * 122 * writes them to the OutputStream. The integer <i>l</i> tells the 123
     * * method how many bytes are required. This is always <= bytesPerAtom().
     * 124
     */
    protected void decodeAtom(PushbackInputStream aStream, OutputStream bStream, int l) throws IOException {
        throw new RuntimeException();
    }

    /**
     * 130 * This method works around the bizarre semantics of
     * BufferedInputStream's 131 * read method. 132
     */
    protected int readFully(InputStream in, byte buffer[], int offset, int len) throws java.io.IOException {
        for (int i = 0; i < len; i++) {
            int q = in.read();
            if (q == -1) return ((i == 0) ? -1 : i);
            buffer[i + offset] = (byte)q;
        }
        return len;
    }

    /**
     * 145 * Decode the text from the InputStream and write the decoded 146 *
     * octets to the OutputStream. This method runs until the stream 147 * is
     * exhausted. 148 * @exception CEFormatException An error has occured while
     * decoding 149 * @exception CEStreamExhausted The input stream is
     * unexpectedly out of data 150
     */
    public void decodeBuffer(InputStream aStream, OutputStream bStream) throws IOException {
        int i;
        @SuppressWarnings("unused")
        int totalBytes = 0;

        PushbackInputStream ps = new PushbackInputStream(aStream);
        decodeBufferPrefix(ps, bStream);
        while (true) {
            int length;

            try {
                length = decodeLinePrefix(ps, bStream);
                for (i = 0; (i + bytesPerAtom()) < length; i += bytesPerAtom()) {
                    decodeAtom(ps, bStream, bytesPerAtom());
                    totalBytes += bytesPerAtom();
                }
                if ((i + bytesPerAtom()) == length) {
                    decodeAtom(ps, bStream, bytesPerAtom());
                    totalBytes += bytesPerAtom();
                } else {
                    decodeAtom(ps, bStream, length - i);
                    totalBytes += (length - i);
                }
                decodeLineSuffix(ps, bStream);
            } catch (RuntimeException e) {
                break;
            }
        }
        decodeBufferSuffix(ps, bStream);
    }

    /**
     * 182 * Alternate decode interface that takes a String containing the
     * encoded 183 * buffer and returns a byte array containing the data. 184 * @exception
     * CEFormatException An error has occured while decoding 185
     */
    @SuppressWarnings("deprecation")
    public byte decodeBuffer(String inputString)[] throws IOException {
        byte inputBuffer[] = new byte[inputString.length()];
        ByteArrayInputStream inStream;
        ByteArrayOutputStream outStream;

        inputString.getBytes(0, inputString.length(), inputBuffer, 0);
        inStream = new ByteArrayInputStream(inputBuffer);
        outStream = new ByteArrayOutputStream();
        decodeBuffer(inStream, outStream);
        return (outStream.toByteArray());
    }

    /**
     * 199 * Decode the contents of the inputstream into a buffer. 200
     */
    public byte decodeBuffer(InputStream in)[] throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        decodeBuffer(in, outStream);
        return (outStream.toByteArray());
    }

    /**
     * 208 * Decode the contents of the String into a ByteBuffer. 209
     */
    public ByteBuffer decodeBufferToByteBuffer(String inputString) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(inputString));
    }

    /**
     * 216 * Decode the contents of the inputStream into a ByteBuffer. 217
     */
    public ByteBuffer decodeBufferToByteBuffer(InputStream in) throws IOException {
        return ByteBuffer.wrap(decodeBuffer(in));
    }
}

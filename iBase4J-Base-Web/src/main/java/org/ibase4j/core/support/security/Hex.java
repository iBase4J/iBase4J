package org.ibase4j.core.support.security;

import java.io.UnsupportedEncodingException;

/**
 * Hex encoder and decoder. The charset used for certain operation can be set,
 * the default is set in
 * 
 * @author ShenHuaJie
 * @version $Id: Hex.java, v 0.1 2014年3月25日 上午9:39:07 ShenHuaJie Exp $
 */
public class Hex {

	/***
	 * Default charset name is {@link CharEncoding#UTF_8}
	 */
	public static final String DEFAULT_CHARSET_NAME = "UTF-8";

	/***
	 * Used to build output as Hex
	 */
	private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/***
	 * Used to build output as Hex
	 */
	private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/***
	 * Converts an array of characters representing hexadecimal values into an
	 * array of bytes of those same values. The returned array will be half the
	 * length of the passed array, as it takes two characters to represent any
	 * given byte. An exception is thrown if the passed char array has an odd
	 * number of elements.
	 * 
	 * @param data An array of characters containing hexadecimal digits
	 * @return A byte array containing binary data decoded from the supplied
	 *         char array.
	 * @throws Exception Thrown if an odd number or illegal of characters is
	 *             supplied
	 */
	public static byte[] decodeHex(char[] data) throws Exception {

		int len = data.length;

		if ((len & 0x01) != 0) {
			throw new Exception("Odd number of characters.");
		}

		byte[] out = new byte[len >> 1];

		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}

		return out;
	}

	/***
	 * Converts an array of bytes into an array of characters representing the
	 * hexadecimal values of each byte in order. The returned array will be
	 * double the length of the passed array, as it takes two characters to
	 * represent any given byte.
	 * 
	 * @param data a byte[] to convert to Hex characters
	 * @return A char[] containing hexadecimal characters
	 */
	public static char[] encodeHex(byte[] data) {
		return encodeHex(data, true);
	}

	/***
	 * Converts an array of bytes into an array of characters representing the
	 * hexadecimal values of each byte in order. The returned array will be
	 * double the length of the passed array, as it takes two characters to
	 * represent any given byte.
	 * 
	 * @param data a byte[] to convert to Hex characters
	 * @param toLowerCase <code>true</code> converts to lowercase,
	 *            <code>false</code> to uppercase
	 * @return A char[] containing hexadecimal characters
	 * @since 1.4
	 */
	public static char[] encodeHex(byte[] data, boolean toLowerCase) {
		return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
	}

	/***
	 * Converts an array of bytes into an array of characters representing the
	 * hexadecimal values of each byte in order. The returned array will be
	 * double the length of the passed array, as it takes two characters to
	 * represent any given byte.
	 * 
	 * @param data a byte[] to convert to Hex characters
	 * @param toDigits the output alphabet
	 * @return A char[] containing hexadecimal characters
	 * @since 1.4
	 */
	protected static char[] encodeHex(byte[] data, char[] toDigits) {
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
			out[j++] = toDigits[0x0F & data[i]];
		}
		return out;
	}

	/***
	 * Converts an array of bytes into a String representing the hexadecimal
	 * values of each byte in order. The returned String will be double the
	 * length of the passed array, as it takes two characters to represent any
	 * given byte.
	 * 
	 * @param data a byte[] to convert to Hex characters
	 * @return A String containing hexadecimal characters
	 * @since 1.4
	 */
	public static String encodeHexString(byte[] data) {
		return new String(encodeHex(data));
	}

	/***
	 * Converts a hexadecimal character to an integer.
	 * 
	 * @param ch A character to convert to an integer digit
	 * @param index The index of the character in the source
	 * @return An integer
	 * @throws Exception Thrown if ch is an illegal hex character
	 */
	protected static int toDigit(char ch, int index) throws Exception {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new Exception("Illegal hexadecimal charcter " + ch + " at index " + index);
		}
		return digit;
	}

	private static String charsetName = DEFAULT_CHARSET_NAME;

	/***
	 * Creates a new codec with the default charset name
	 * {@link #DEFAULT_CHARSET_NAME}
	 */
	public Hex() {
	}

	/***
	 * Creates a new codec with the given charset name.
	 * 
	 * @param csName the charset name.
	 * @since 1.4
	 */
	public Hex(String csName) {
		charsetName = csName;
	}

	/***
	 * Converts an array of character bytes representing hexadecimal values into
	 * an array of bytes of those same values. The returned array will be half
	 * the length of the passed array, as it takes two characters to represent
	 * any given byte. An exception is thrown if the passed char array has an
	 * odd number of elements.
	 * 
	 * @param array An array of character bytes containing hexadecimal digits
	 * @return A byte array containing binary data decoded from the supplied
	 *         byte array (representing characters).
	 * @throws Exception Thrown if an odd number of characters is supplied to
	 *             this function
	 * @see #decodeHex(char[])
	 */
	public byte[] decode(byte[] array) throws Exception {
		try {
			return decodeHex(new String(array, getCharsetName()).toCharArray());
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/***
	 * Converts a String or an array of character bytes representing hexadecimal
	 * values into an array of bytes of those same values. The returned array
	 * will be half the length of the passed String or array, as it takes two
	 * characters to represent any given byte. An exception is thrown if the
	 * passed char array has an odd number of elements.
	 * 
	 * @param object A String or, an array of character bytes containing
	 *            hexadecimal digits
	 * @return A byte array containing binary data decoded from the supplied
	 *         byte array (representing characters).
	 * @throws Exception Thrown if an odd number of characters is supplied to
	 *             this function or the object is not a String or char[]
	 * @see #decodeHex(char[])
	 */
	public Object decode(Object object) throws Exception {
		try {
			char[] charArray = object instanceof String ? ((String) object).toCharArray() : (char[]) object;
			return decodeHex(charArray);
		} catch (ClassCastException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/***
	 * Converts an array of bytes into an array of bytes for the characters
	 * representing the hexadecimal values of each byte in order. The returned
	 * array will be double the length of the passed array, as it takes two
	 * characters to represent any given byte.
	 * <p>
	 * The conversion from hexadecimal characters to the returned bytes is
	 * performed with the charset named by {@link #getCharsetName()}.
	 * </p>
	 * 
	 * @param array a byte[] to convert to Hex characters
	 * @return A byte[] containing the bytes of the hexadecimal characters
	 * @throws IllegalStateException if the charsetName is invalid. This API
	 *             throws {@link IllegalStateException} instead of
	 *             {@link Exception} for backward compatibility.
	 * @see #encodeHex(byte[])
	 */
	public static byte[] encode(byte[] array) throws UnsupportedEncodingException {
		String string = encodeHexString(array);
		if (string == null) {
			return null;
		}
		return string.getBytes(charsetName);
	}

	/***
	 * Converts a String or an array of bytes into an array of characters
	 * representing the hexadecimal values of each byte in order. The returned
	 * array will be double the length of the passed String or array, as it
	 * takes two characters to represent any given byte.
	 * <p>
	 * The conversion from hexadecimal characters to bytes to be encoded to
	 * performed with the charset named by {@link #getCharsetName()}.
	 * </p>
	 * 
	 * @param object a String, or byte[] to convert to Hex characters
	 * @return A char[] containing hexadecimal characters
	 * @throws Exception Thrown if the given object is not a String or byte[]
	 * @see #encodeHex(byte[])
	 */
	public Object encode(Object object) throws Exception {
		try {
			byte[] byteArray = object instanceof String ? ((String) object).getBytes(getCharsetName())
					: (byte[]) object;
			return encodeHex(byteArray);
		} catch (ClassCastException e) {
			throw new Exception(e.getMessage(), e);
		} catch (Exception e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/***
	 * Gets the charset name.
	 * 
	 * @return the charset name.
	 * @since 1.4
	 */
	public String getCharsetName() {
		return charsetName;
	}

	/***
	 * Returns a string representation of the object, which includes the charset
	 * name.
	 * 
	 * @return a string representation of the object.
	 */
	public String toString() {
		return super.toString() + "[charsetName=" + charsetName + "]";
	}
}

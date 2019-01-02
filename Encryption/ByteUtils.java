package com.example.demo.controller.uitls;

public class ByteUtils {

	/**
	 * @Date	2018.12.20
	 * @param	bytes - unsigned byte's array
	 * @return	HexUtils.toString(byte[])
	 */
	public static String toHexString(byte[] bytes) {

		StringBuffer result = new StringBuffer();

		if (bytes != null) {

			for (byte b : bytes) {
				result.append(Integer.toString((b & 0xF0) >> 4, 16));
				result.append(Integer.toString(b & 0x0F, 16));
			}
			return result.toString();

		} else {
			return null;
		}
	}

	/**
	 * @Date	2018.12.20
	 * @throws	IllegalArgumentException, NumberFormatException
	 * @param	digits - Hexadecimal string
	 * @return	byte[]
	 */
	public static byte[] toBytesFromHexString(String digits) throws IllegalArgumentException, NumberFormatException {

		if (digits == null) {
			return null;
		}

		int length = digits.length();
		if (length % 2 == 1) {
			throw new IllegalArgumentException("For input string: \"" + digits + "\"");
		}

		length = length / 2;
		byte[] bytes = new byte[length];
		for (int i = 0; i < length; i++) {
			int index = i * 2;
			bytes[i] = (byte) (Short.parseShort(digits.substring(index, index + 2), 16));
		}
		return bytes;
	}
}

package com.cecilectomy.dmge.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * DeadMarsLib SecurityUtility Class
 * 
 * @author Daniel Cecil
 */
public class Cryptography {

	/**
	 * Create an MD5 hash of a string.
	 * 
	 * @deprecated
	 * 
	 * @param md5 String to hash.
	 * @return String representation of hash.
	 */
	public static String MD5(String md5) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes("UTF-8"));
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
						.substring(1, 3));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String textHash(MessageDigest algorithm, String text)
			throws UnsupportedEncodingException {
		byte[] digest = algorithm.digest(text.getBytes("UTF-8"));
		StringBuilder sb = new StringBuilder();
		for (byte b : digest)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();

	}

	public static String fileHash(MessageDigest algorithm, String fileName)
			throws FileNotFoundException, IOException {
		InputStream is = new FileInputStream(fileName);
		is = new DigestInputStream(is, algorithm);
		while (is.read() > -1) {
		}
		is.close();
		byte[] digest = algorithm.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : digest)
			sb.append(String.format("%02x", b & 0xff));
		return sb.toString();
	}

}

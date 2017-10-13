package com.ifly.transporter.utils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


public class Encodes {

	public static String encodeHex(byte[] input){
		return Hex.encodeHexString(input);
	}
	public static byte[] decodeHex(String input){
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static byte[] decodeBase64(String input){
		return Base64.decodeBase64(input);
	}
	public static String encodeBase64(byte[] input){
		return Base64.encodeBase64String(input);
	}
}

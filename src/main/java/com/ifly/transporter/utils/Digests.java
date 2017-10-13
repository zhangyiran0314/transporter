package com.ifly.transporter.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Digests{
	private static final String SHA1="SHA-1";
	private static final String MD5="MD5";
	public static byte[] sha1(byte[] input,byte[] salt,int iterations){
		return digest(input,SHA1,salt,iterations);
	}
	private static byte[] digest(byte[] input,String algorithm,byte[] salt,int iterations){
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			if(salt !=null){
				digest.update(salt);
			}
			byte[] result = digest.digest(input);
			for(int i =1;i<iterations;i++){
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
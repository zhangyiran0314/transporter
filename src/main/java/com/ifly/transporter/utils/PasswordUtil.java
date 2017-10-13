package com.ifly.transporter.utils;

public class PasswordUtil {

	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE =8;
	
	public static void main(String[] args){
		String user = "admin";
		String password = "admin";
		//打印salt
		System.out.println(Encodes.encodeHex(generateSalt(user,password)));
		//打印password
		System.out.println(generatePassword(user,password).getPassword());
	}
	public static Password generatePassword(String user,String password){
		byte[] salt = generateSalt(user,password);
		
		byte[] hashPassword = Digests.sha1(password.getBytes(), salt,HASH_INTERATIONS);
		password = Encodes.encodeHex(hashPassword);
		return new Password(password,salt);
	}
	public static byte[] generateSalt(String user,String password){
		byte[] salt = new byte[SALT_SIZE];
		byte[] passwordByte = Encodes.decodeBase64(password);
		byte[] userByte = Encodes.decodeBase64(user);
		for (int i = 0; (i < passwordByte.length) && (i < salt.length); i++) {
			salt[i] = passwordByte[i];
		}
		for (int i = 0; (i < userByte.length) && (i < salt.length - 4); i++) {
			salt[(i + 4)] = userByte[i];
		}
		return salt;	 
	}
	
	public static class Password{
		private byte[] salt;
		private String password;
		
		public byte[] getSalt() {
			return salt;
		}

		public String getPassword() {
			return password;
		}

		public Password(String password,byte[] salt){
			this.salt = salt;
			this.password = password;
		}
	}
}

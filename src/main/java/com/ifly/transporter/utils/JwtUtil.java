package com.ifly.transporter.utils;


import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONObject;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static String JWT_SECRET="zhangsan";
	
	public static final int JWT_TTL = 60*60*1000;  //millisecond
	public static final int JWT_REFRESH_INTERVAL = 55*60*1000;  //millisecond
	public static final int JWT_REFRESH_TTL = 12*60*60*1000;  //millisecond
	/**
	 * 创建jwt
	 * @param name 用户名
	 * @return
	 */
	public static String createJWT(String name,long ttlMillis ){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		SecretKey key = generalKey();
		JwtBuilder builder = Jwts.builder()
				.setSubject(name)
				.setIssuedAt(now)
				.signWith(signatureAlgorithm, key);
		if (ttlMillis >= 0) {
		    long expMillis = nowMillis + ttlMillis;
		    Date exp = new Date(expMillis);
		    builder.setExpiration(exp);
		}
		return builder.compact();
	}
	
	private static SecretKey generalKey() {
		byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
	    SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}
	/**
	 * 解密jwt
	 * @param jwt
	 * @return
	 * @throws Exception
	 */
	public static Claims parseJWT(String jwt){
		try{
			SecretKey key = generalKey();
			Claims claims = Jwts.parser()         
			   .setSigningKey(key)
			   .parseClaimsJws(jwt).getBody();
			
			if(claims!=null){
				System.out.println("ID: " + claims.getId());
				System.out.println("Subject: " + claims.getSubject());
				System.out.println("Issuer: " + claims.getIssuer());
				System.out.println("Expiration: " + claims.getExpiration());
				return claims;
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	/**
	 * 生成subject信息
	 * @param user
	 * @return
	 *//*
	public static String generalSubject(User user){
		JSONObject jo = new JSONObject();
		jo.put("userId", user.getUserId());
		jo.put("roleId", user.getRoleId());
		return jo.toJSONString();
	}*/
}

package com.ifly.transporter.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String UUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args){
		System.out.println(UUIDUtil.UUID());
	}
}

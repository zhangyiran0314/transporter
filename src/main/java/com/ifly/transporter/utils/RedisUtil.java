package com.ifly.transporter.utils;

public class RedisUtil {
	/**redis模块划分*/
	//验证码模块
	public static String Redis_Captcha ="captcha";
	
	/**获取key值*/
	public static String getCaptchaKey(String key){
		return Redis_Captcha+":"+key; 
	}
}

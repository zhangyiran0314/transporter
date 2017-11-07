package com.ifly.transporter.common.exception;

public class ErrCode {
	//未知错误
	public static int UnknownError = 1000;
	public static String UnknownError_Value = "UnknownError";
	
	//重复获取验证码
	public static int RepeatForCaptchaError=1001;
	//重复获取验证码
	public static int VerifyCaptchaError=1002;
	
	//账户已经存在
	public static int AccountsAlreadyExist = 1003;
	//账户密码不正确
	public static int AccountOrPasswordError = 1004;
}

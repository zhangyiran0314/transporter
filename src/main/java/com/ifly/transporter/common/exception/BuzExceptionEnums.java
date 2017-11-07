package com.ifly.transporter.common.exception;

public enum BuzExceptionEnums implements ExceptionEnums{
	//未知错误
	UnknownError(ErrCode.UnknownError,"UnknownError"),
	//重复获取验证码
	RepeatForCaptchaError(ErrCode.RepeatForCaptchaError,"RepeatForCaptchaError"),
	//验证验证码错误
	VerifyCaptchaError(ErrCode.VerifyCaptchaError,"VerifyCaptchaError"),
	//账户已经注册
	AccountsAlreadyExist(ErrCode.AccountsAlreadyExist,"AccountsAlreadyExist"),
	//账号密码错误
	AccountOrPasswordErr(ErrCode.AccountOrPasswordError, "AccountOrPasswordErr");
	
	public int code;
	public String message;
	
    private BuzExceptionEnums(int code, String message){  
        this.code = code;  
        this.message = message;  
    }  
	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getMessage() {
		return message;
	}
}

package com.ifly.transporter.common.exception;

public enum DBExceptionEnums implements ExceptionEnums{
	
	UNIQUE_KEY(10001, "主键约束错误");
	
	public int code;
	public String message;
	
    private DBExceptionEnums(int code, String message){  
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

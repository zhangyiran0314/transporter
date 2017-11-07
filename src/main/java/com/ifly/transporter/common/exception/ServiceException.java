package com.ifly.transporter.common.exception;

public class ServiceException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	private ExceptionEnums exceptionEnums;
	
	public ServiceException(ExceptionEnums exceptionEnums){  
        this.exceptionEnums = exceptionEnums;  
	}  
	
	public ExceptionEnums getExceptionEnums() {
		return exceptionEnums;
	}

	public void setExceptionEnums(ExceptionEnums exceptionEnums) {
		this.exceptionEnums = exceptionEnums;
	}
	public int getCode(){
		if(exceptionEnums!=null) return exceptionEnums.getCode();
		return ErrCode.UnknownError;
	}
	public String getMessage(){
		if(exceptionEnums!=null)  return exceptionEnums.getMessage();
		return ErrCode.UnknownError_Value;
	}
}

package com.ifly.transporter.shiro;

import java.io.Serializable;

import org.apache.shiro.authc.UsernamePasswordToken;

public class ShiroToken extends UsernamePasswordToken implements Serializable{

	private static final long serialVersionUID = 3523350995375886819L;
	/** 登录密码[字符串类型] 因为父类是char[] ] **/
	private String pswd ;

	public ShiroToken(String username,String pswd){
		super(username,pswd);
		this.pswd = pswd;
	}
	public String getPswd() {
		return pswd;
	}


	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
}

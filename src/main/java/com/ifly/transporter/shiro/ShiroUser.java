package com.ifly.transporter.shiro;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

public class ShiroUser implements Serializable{
	
	private static final long serialVersionUID = 5862459554147052329L;
	
	private Long id;
	private String loginName;
	private String name;
	private List<String> roles = Lists.newArrayList();
	
	public ShiroUser(Long id, String loginName, String name) {
		super();
		this.id = id;
		this.loginName = loginName;
		this.name = name;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
}

package com.ifly.transporter.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.ifly.transporter.shiro.ShiroDbRealm;
import com.ifly.transporter.shiro.ShiroRealm;
import com.ifly.transporter.shiro.ShiroUser;

public class UserUtil {
	public static ShiroUser getUser(){
		Subject subject = null;
		subject = SecurityUtils.getSubject();
		if(subject == null){
			return null;
		}
		ShiroUser user = (ShiroUser) subject.getPrincipal();
		return user;
	}
	public static Session getSessioin()
	{
	  return SecurityUtils.getSubject().getSession();
	}
	
	public static Subject getSubject() {
	  return SecurityUtils.getSubject();
	}

	public static void logout() {
		Subject subject = SecurityUtils.getSubject();
		ShiroRealm shiroDbRealm = (ShiroDbRealm) SpringBeanFactory.getBean(ShiroRealm.class);
//		shiroDbRealm.exit((ShiroUser) subject.getPrincipal());
		subject.logout();
	}

	public static boolean isPermitted(String permit) {
		Subject subject = SecurityUtils.getSubject();
		return subject.isPermitted(permit);
	}
}

package com.ifly.transporter.shiro;

import java.util.Date;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ifly.transporter.sys.bean.UUser;
import com.ifly.transporter.sys.service.PermissionService;
import com.ifly.transporter.sys.service.RoleService;
import com.ifly.transporter.sys.service.UUserService;
import com.ifly.transporter.utils.PasswordUtil;

public class ShiroDbRealm extends ShiroRealm{
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MShiroFilterFactoryBean.class);
	
	@Autowired
	UUserService userService;
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	
	/**
	 * 认证,主要对应用户登录
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		ShiroToken tk = (ShiroToken) token;
		LOGGER.info("当前用户token,username={},password={}",tk.getUsername(),tk.getPswd());
		
		PasswordUtil.Password password = PasswordUtil.generatePassword(tk.getUsername(), tk.getPswd());
		UUser user = userService.login(tk.getUsername(), password.getPassword());
		if(null == user){
			throw new AccountException("帐号或密码不正确！");
		/**
		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
		 */
		}else if(UUser._0.equals(user.getStatus())){
			throw new DisabledAccountException("帐号已经禁止登录！");
		}else{
			//更新登录时间 last login time
			user.setLastLoginTime(new Date());
			userService.updateByPrimaryKeySelective(user);
		}
//		return new SimpleAuthenticationInfo(user,user.getPswd(), getName());
		return new  SimpleAuthenticationInfo(new ShiroUser(user.getId(),user.getNickname(),user.getEmail()),                                                                                   
		        user.getPswd(),                                                                                                                               
		        ByteSource.Util.bytes(password.getSalt()), getName()); 
	}
	/**
	 * 授权,对应用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
		//根据用户ID查询角色（role），放入到Authorization里。
		Set<String> roles = roleService.findRoleByUserId(shiroUser.getId());
		info.setRoles(roles);
		//根据用户ID查询权限（permission），放入到Authorization里。
		Set<String> permissions = permissionService.findPermissionByUserId(shiroUser.getId());
		info.setStringPermissions(permissions);
        return info;  
	}
	/**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清除
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}

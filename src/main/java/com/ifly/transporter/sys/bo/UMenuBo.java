package com.ifly.transporter.sys.bo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.ifly.transporter.sys.bean.UMenu;

/**
 * 
 * 权限选择
 * @author zhou-baicheng
 *
 */
public class UMenuBo extends UMenu implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 是否勾选
	 */
	private String marker;
	/**
	 * role Id
	 */
	private String roleId;

	public boolean isCheck(){
		return StringUtils.equals(roleId,marker);
	}
	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}

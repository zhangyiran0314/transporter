package com.ifly.transporter.api.service;

import com.ifly.transporter.api.bean.UserShipper;

public interface UserShipperService  {
	//通过mobile查询用户
	public UserShipper queryByMobile(String mobile);
	//注册
	public int register(UserShipper record);
	//修改
	public int update(UserShipper record);
	//通过账户密码查询用户
	public UserShipper login(String mobile,String password);
}

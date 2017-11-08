package com.ifly.transporter.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifly.transporter.api.bean.UserShipper;
import com.ifly.transporter.api.mapper.UserShipperMapper;
import com.ifly.transporter.api.service.UserShipperService;

@Service("userShipperService")
public class UserShipperServiceImpl implements UserShipperService{

	@Autowired
	private UserShipperMapper userShipperMapper;
	
	@Override
	public UserShipper queryByMobile(String mobile) {
		return userShipperMapper.selectByMobile(mobile);
	}

	@Override
	public int register(UserShipper record) {
		return userShipperMapper.insert(record);
	}

	@Override
	public int update(UserShipper record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserShipper login(String mobile, String password) {
		return userShipperMapper.selectByMobileAndPassword(mobile, password);
	}

}

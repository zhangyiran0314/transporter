package com.ifly.transporter.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifly.transporter.sys.bean.User;
import com.ifly.transporter.sys.mapper.UserMapper;
import com.ifly.transporter.sys.service.UserService;

@Service("userservice")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	@Override
	public int add(User record) {
		return userMapper.insert(record);
	}

	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User loginUser(String username,String pwd) {
		return null;
	}

}

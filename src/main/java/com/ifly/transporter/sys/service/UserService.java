package com.ifly.transporter.sys.service;

import com.ifly.transporter.sys.bean.User;

public interface UserService {
	 
	User loginUser(String username,String pwd);
	
	int add(User record);

	User selectByPrimaryKey(Long id);
}

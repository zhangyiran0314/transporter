package com.ifly.transporter.api.mapper;

import org.apache.ibatis.annotations.Param;

import com.ifly.transporter.api.bean.UserShipper;

public interface UserShipperMapper {
	
	//根据mobile查询
	UserShipper selectByMobile(@Param("mobile")String mobile);
	//根据mobile和密码查询用户
	UserShipper selectByMobileAndPassword(@Param("mobile")String mobile,@Param("password")String password);
	
    int deleteByPrimaryKey(String id);

    int insert(UserShipper record);

    int insertSelective(UserShipper record);

    UserShipper selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(UserShipper record);

    int updateByPrimaryKey(UserShipper record);
}
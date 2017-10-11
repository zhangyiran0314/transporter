package com.ifly.transporter.sys.mapper;

import com.ifly.transporter.sys.bean.UserRole;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);
}
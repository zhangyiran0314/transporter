package com.ifly.transporter.sys.mapper;

import com.ifly.transporter.sys.bean.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}
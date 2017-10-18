package com.ifly.transporter.sys.mapper;

import java.util.List;
import java.util.Set;

import com.ifly.transporter.sys.bean.UPermission;
import com.ifly.transporter.sys.bo.UPermissionBo;

public interface UPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    UPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

	List<UPermissionBo> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
	
	List<UPermission> findMenusByUserId(Long userId);
}
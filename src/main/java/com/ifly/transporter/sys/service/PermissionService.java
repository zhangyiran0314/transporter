package com.ifly.transporter.sys.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ifly.transporter.sys.bean.UMenu;
import com.ifly.transporter.sys.bo.UMenuBo;

public interface PermissionService {

	int deleteByPrimaryKey(Long id);

	UMenu insert(UMenu record);

    UMenu insertSelective(UMenu record);

    UMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UMenu record);

    int updateByPrimaryKey(UMenu record);

	Map<String, Object> deletePermissionById(String ids);

	/*Pagination<UPermission> findPage(Map<String,Object> resultMap, Integer pageNo,
			Integer pageSize);*/
	List<UMenuBo> selectPermissionById(Long id);

	Map<String, Object> addPermission2Role(Long roleId,String ids);

	Map<String, Object> deleteByRids(String roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
	
	//根据用户ID查询权限（permission），放入到Authorization里。
	List<Map<String,Object>>  findMenusByUserId(Long userId);
}

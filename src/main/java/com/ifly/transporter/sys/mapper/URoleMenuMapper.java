package com.ifly.transporter.sys.mapper;

import java.util.List;
import java.util.Map;

import com.ifly.transporter.sys.bean.URoleMenu;

public interface URoleMenuMapper {
    int insert(URoleMenu record);

    int insertSelective(URoleMenu record);

	List<URoleMenu> findRolePermissionByPid(Long id);
	
	List<URoleMenu> findRolePermissionByRid(Long id);
	
	List<URoleMenu> find(URoleMenu entity);
	
	int deleteByPid(Long id);
	int deleteByRid(Long id);
	int delete(URoleMenu entity);

	int deleteByRids(Map<String,Object> resultMap);
}
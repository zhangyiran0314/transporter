package com.ifly.transporter.sys.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ifly.transporter.sys.bean.UMenu;
import com.ifly.transporter.sys.bo.UMenuBo;

public interface UMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UMenu record);

    int insertSelective(UMenu record);

    UMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UMenu record);

    int updateByPrimaryKey(UMenu record);

	List<UMenuBo> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
	
	List<Map<String,Object>>  findMenusByUserId(Long userId);
}
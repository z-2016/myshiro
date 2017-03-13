package com.lx.shiro.service;

import java.util.List;
import java.util.Set;

import com.lx.domain.Role;

public interface RoleService {
	int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    Set<Role> selectRoleByRoleId(List<Integer> listRoleId);
}

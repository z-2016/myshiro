package com.lx.shiro.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.RoleDao;
import com.lx.domain.Role;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;

	public int deleteByPrimaryKey(Integer id) {
		roleDao.deleteByPrimaryKey(id);
		return 0;
	}

	public int insert(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Role selectByPrimaryKey(Integer id) {
		return roleDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Role record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Set<Role> selectRoleByRoleId(List<Integer> listRoleId) {
		return roleDao.selectRoleByRoleId(listRoleId);
	}

}

package com.lx.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserRoleDao;
import com.lx.domain.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired
	private UserRoleDao userRoleDao;
	
	public int deleteByPrimaryKey(Integer id) {
		userRoleDao.selectByPrimaryKey(id);
		return 0;
	}

	public int insert(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int insertSelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public UserRole selectByPrimaryKey(Integer id) {
		return userRoleDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(UserRole record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<UserRole> selectByUserId(int userId) {
		return userRoleDao.selectByUserId(userId);
	}

}

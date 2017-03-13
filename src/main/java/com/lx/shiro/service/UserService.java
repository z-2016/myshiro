package com.lx.shiro.service;

import java.util.List;

import com.lx.domain.User;

public interface UserService {
	int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUsername(String username);
    
    List<User> listUser();
}

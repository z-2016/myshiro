package com.lx.shiro.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lx.dao.UserDao;
import com.lx.domain.User;
import com.lx.shiro.util.PasswordEncrypt;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	private PasswordEncrypt passwordService;
	
	public int deleteByPrimaryKey(Integer id) {
		userDao.deleteByPrimaryKey(id);
		return 0;
	}

	public int insert(User user) {
		User u = userDao.selectByUsername(user.getUsername());
		if(u==null){
			String password = user.getPassword();
			passwordService = new PasswordEncrypt();
			String encryptPassword = passwordService.encryptPassword(password);
			user.setPassword(encryptPassword);
			userDao.insert(user);
		}
		return 0;
	}

	public int insertSelective(User record) {
		userDao.insertSelective(record);
		return 0;
	}

	public User selectByPrimaryKey(Integer id) {
		userDao.selectByPrimaryKey(id);
		return null;
	}

	public int updateByPrimaryKeySelective(User record) {
		userDao.updateByPrimaryKeySelective(record);
		return 0;
	}

	public int updateByPrimaryKey(User record) {
		userDao.updateByPrimaryKey(record);
		return 0;
	}

	public User selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	public List<User> listUser() {
		return userDao.listUser();
	}

}

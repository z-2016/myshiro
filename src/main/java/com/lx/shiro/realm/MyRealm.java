package com.lx.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lx.domain.Role;
import com.lx.domain.User;
import com.lx.domain.UserRole;
import com.lx.shiro.Service.RoleService;
import com.lx.shiro.Service.UserRoleService;
import com.lx.shiro.Service.UserService;

public class MyRealm extends AuthorizingRealm {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRoleService userRoleService;
	
	@Autowired
	RoleService roleService;
	
	private CredentialsMatcher credentialsMatch;
	
	//授权
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String username = (String) principal.getPrimaryPrincipal();
		int userId = userService.selectByUsername(username).getId();
		List<UserRole> userRoleList = userRoleService.selectByUserId(userId);
		List<Integer> list=new ArrayList<Integer>();
		for(UserRole u:userRoleList){
			list.add(u.getRoleId());
		}
		Set<Role> roleSet = roleService.selectRoleByRoleId(list);
		SimpleAuthorizationInfo authorization=new SimpleAuthorizationInfo();
		Set<String> set=new HashSet<String>();
		for(Role r:roleSet){
			set.add(r.getRolename());
		}
		authorization.setRoles(set);
		return authorization;
	}

	//认证
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户名
		String username=(String) token.getPrincipal();
		String password =new String((char[])token.getCredentials());
		
		User user = userService.selectByUsername(username);
		
//		retryLimitHashCredentailMatcher = new RetryLimitHashCredentailMatcher();
//		boolean doCredentialsMatch = retryLimitHashCredentailMatcher.doCredentialsMatch(token, simpleAuthenticationInfo);
		
//		if(doCredentialsMatch){
//			return simpleAuthenticationInfo;
//		}
		if(!username.equals(user.getUsername())){
			throw new UnknownAccountException();
		}
		
		if(!password.equals(user.getPassword())){
			throw new IncorrectCredentialsException();
		}
		
		AuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password,getName());
		return simpleAuthenticationInfo;
		
	}

}

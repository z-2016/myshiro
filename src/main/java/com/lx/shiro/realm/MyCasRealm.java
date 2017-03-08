package com.lx.shiro.realm;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.lx.shiro.Service.UserService;

public class MyCasRealm extends CasRealm{
	
	@Autowired
	private UserService userService;
	
	 protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
		 
		 String username=(String)principals.getPrimaryPrincipal();
		 SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		 return info;
	 }
	 
	 
	 @Override
	public String getCasServerUrlPrefix() {
		return "http://localhost:8080/myshiro/user/login";
	}
	 
	 @Override
	public String getCasService() {
		//单点登录的地址
		return "http://";
	}
}

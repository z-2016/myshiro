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
		 Set set=new HashSet();
		 set.add("admin");
		 info.setRoles(set);
		 Set set1=new HashSet();
		 set1.add("add");
		 info.setStringPermissions(set1);
		 return info;
	 }
}

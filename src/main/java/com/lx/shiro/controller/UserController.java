package com.lx.shiro.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lx.domain.User;
import com.lx.shiro.Service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,@RequestParam("password") String password){
		try{
			UsernamePasswordToken token=new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
		}catch(AuthenticationException e){
			e.getStackTrace();
			return "login";
		}
		return "list";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	@RequiresRoles({"admin"})
	@RequiresAuthentication
	public String list(Model model) {
		
		List<User> users = userService.listUser();
		model.addAttribute("users", users);
		
		return "list";
	}
	 
}

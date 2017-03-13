package com.lx.shiro.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lx.domain.User;
import com.lx.shiro.service.UserService;
import com.lx.shiro.util.PasswordEncrypt;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private PasswordEncrypt passwordService; 
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(){
		return "/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username") String username,@RequestParam("password") String password){
		try{
			passwordService = new PasswordEncrypt();
			String encryptPassword = passwordService.encryptPassword(password);
			UsernamePasswordToken token=new UsernamePasswordToken(username, encryptPassword);
			token.setRememberMe(true);
			
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
			subject.getSession(false).setAttribute("subject", subject);
		}catch(AuthenticationException e){
			e.getStackTrace();
			return "login";
		}
		return "redirect:list";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public String logout(){
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}
	
	@RequestMapping(value="/list",method = RequestMethod.GET)
	@RequiresRoles({"admin"})
//	@RequiresAuthentication
	public String list(Model model) {
		
		List<User> users = userService.listUser();
		model.addAttribute("users", users);
		Subject subject = SecurityUtils.getSubject();
		boolean hasRole = subject.hasRole("admin");
		System.out.println(hasRole+"-----");
		return "/list";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String register() {
		
		return "/register";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String register(@ModelAttribute User user) {
		userService.insert(user);
		return "/login";
	}
	 
}

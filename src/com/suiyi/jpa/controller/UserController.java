package com.suiyi.jpa.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.service.UserService;

@Controller
@RequestMapping
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/user_login.do")
	public String login(String nickname,String password,HttpServletRequest request){
		User user=userService.login(nickname, password);
		if(user==null){
			request.setAttribute("login_error","用户不存在");
			return "index";
		}
		if(!StringUtils.equals(user.getPassword(), password)){
			request.setAttribute("login_error","用户名密码不正确");
			return "redirect:index";		
		}
		request.setAttribute("user", user);
		request.setAttribute("type", 1);
		return "user_login";
	}
	
	@RequestMapping(value = "/user_regist.do")
	public ModelAndView regist(String nickname,String password,String telephone){
		User user=userService.findByNickname(nickname);
		User user1=userService.findByPhone(telephone);
		if(user!=null){
			return new ModelAndView("index","regist_error","用户名已存在");
		}
		if(user1!=null){
			return new ModelAndView("index","regist_error","手机号码已注册");
		}
			user=new User();
			user.setNickname(nickname);
			user.setPhone(telephone);
			user.setPassword(password);
			user.setCreatedBy(nickname);
			user.setCreateTime(new Date());
			user.setUpdatedBy(nickname);
			user.setUpdateTime(new Date());
			user=userService.add(user);
			return new ModelAndView("user_login","user",user);
	}
	
	@RequestMapping(value = "/user_detail.do")
	public ModelAndView detail(String nickname,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		request.setAttribute("type",0);
		return new ModelAndView("user_login","user",user);
	}

}

package com.suiyi.jpa.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.bean.UserLocation;
import com.suiyi.jpa.service.UserLocationService;
import com.suiyi.jpa.service.UserService;

@Controller
@RequestMapping
public class UserController  {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLocationService userLocationService;
	
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
		request.setAttribute("userName", nickname);
		request.setAttribute("user", user);
		request.setAttribute("type", 1);
		return "user_login";
	}
	
	@RequestMapping(value = "/user_info.do")
	public String userInfo(String nickname,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		request.setAttribute("userName", user.getNickname());
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
	
	@RequestMapping(value = "/user_update.do")
	public ModelAndView update(String nickname,Integer id,String name,String password,String telephone,HttpServletRequest request){
		User u=userService.findById(id);
		User user=userService.findByNickname(nickname);
		User user1=userService.findByPhone(telephone);
		if(u.getId()!=user.getId()){
			request.setAttribute("type",0);
			return new ModelAndView("user_login","regist_error","用户名已存在");
		}
		if(u.getId()!=user1.getId()){
			request.setAttribute("type",0);
			return new ModelAndView("index","regist_error","手机号码已注册");
		}
			u.setNickname(nickname);
			u.setPhone(telephone);
			u.setPassword(password);
			u.setName(name);
			userService.fillUpdate(u, nickname);
			user=userService.save(user);
			request.setAttribute("type",1);
			request.setAttribute("userName", user.getNickname());
			return new ModelAndView("user_login","user",user);
	}
	
	@RequestMapping(value = "/user_detail.do")
	public ModelAndView detail(String nickname,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		request.setAttribute("type",0);
		request.setAttribute("userName", nickname);
		return new ModelAndView("user_login","user",user);
	}
	
	@RequestMapping(value = "/add_location.do")
	public ModelAndView addLocation(String nickname,String location,String connector,String phone,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		UserLocation userLocation=new UserLocation();
		userLocation.setPhone(phone);
		userLocation.setConnector(connector);
		userLocation.setLocation(location);
		userLocation.setUserId(user.getId());
		userLocation=userLocationService.add(userLocation,nickname);
		request.setAttribute("type", 1);
		request.setAttribute("userName", nickname);
		return new ModelAndView("user_login","user",user);
	}
	
	@RequestMapping(value = "/update_location.do")
	public ModelAndView updateLocation(Integer id,String nickname,String location,String connector,String phone,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		UserLocation userLocation=userLocationService.findById(id);
		userLocation.setLocation(location);
		userLocation.setConnector(connector);
		userLocation.setPhone(phone);
		userLocation=userLocationService.fillUpdate(userLocation, nickname);
		userLocationService.save(userLocation);
		request.setAttribute("type", 1);
		request.setAttribute("success","修改成功");
		request.setAttribute("userName", nickname);
		return new ModelAndView("user_login","user",user);
	}
	
	@RequestMapping(value = "/delete_location.do")
	public ModelAndView deleteLocation(Integer id,String nickname,HttpServletRequest request){
		User user=userService.findByNickname(nickname);
		userLocationService.deleteLocation(id);
		request.setAttribute("type", 1);
		request.setAttribute("userName", nickname);
		return new ModelAndView("user_login","user",user);
	}

}

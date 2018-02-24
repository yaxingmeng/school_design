package com.suiyi.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.service.AdminUserService;

@Controller
@RequestMapping
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;

    @RequestMapping(value = "/check.do")
    public ModelAndView adminLogin(String name, String password) {
    	
        String a="aaa";
        return new ModelAndView("test");
    }
}

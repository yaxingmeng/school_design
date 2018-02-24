package com.suiyi.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.bean.AdminUser;
import com.suiyi.jpa.service.AdminUserService;

@Controller
@RequestMapping
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;

	@RequestMapping(value = "/check.do")
	public ModelAndView adminLogin(String name, String password) {
		AdminUser adminuser = adminUserService.login(name, password);
		return new ModelAndView("admin_login", "admin", adminuser);
	}

	@RequestMapping(value = "/add_admin.do")
	public void addAdmin(String no, String name, String password, String right,String operator) {
		System.out.println(right+"..."+operator);
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminNo(no);
		adminUser.setName(name);
		adminUser.setPassword(password);
		adminUser.setRight(right);
		adminUser.setType(EnumName.AdminType.NORMAL_ADMIN.getValue());
		adminUserService.add(adminUser);
		
	}
}

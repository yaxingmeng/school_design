package com.suiyi.jpa.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
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

	@RequestMapping(value = "/addAdmin.do")
	public String addAdmin(String operator,String no, String name, String password,Integer right) {
		System.out.println(right+"..."+operator);
		AdminUser adminUser = new AdminUser();
		adminUser.setAdminNo(no);
		adminUser.setName(name);
		adminUser.setPassword(password);
		adminUser.setRights(right);
		adminUser.setType(EnumName.AdminType.NORMAL_ADMIN.getValue());
		adminUser.setCreatedBy(operator);
		adminUser.setCreateTime(new Date());
		adminUser.setUpdateTime(new Date());
		adminUser.setUpdatedBy(operator);
		adminUserService.add(adminUser);
		return "succes";
	}
	
	@RequestMapping(value = "/adminList.do")
	public ModelAndView adminList(Integer pagesize,Integer pagenumber,HttpServletRequest request){
		int totalcount=adminUserService.list().size();
		int pagecount = 0;
		int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if(pagenumber>pagecount||pagenumber<0){
        	throw new ExceptionMessage("页数有错");
        }
		Page<AdminUser> adminuser=adminUserService.adminList(pagenumber-1, pagesize);
		List<AdminUser> admin=adminuser.getContent();
		List<Integer> page=new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		return new ModelAndView("adminList", "admin", admin);
	}
}

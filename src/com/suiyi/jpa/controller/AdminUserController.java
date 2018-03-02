package com.suiyi.jpa.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.AdminUser;
import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.service.AdminUserService;
import com.suiyi.jpa.service.GoodTypeService;

@Controller
@RequestMapping
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private GoodTypeService goodTypeService;

	@RequestMapping(value = "/check.do")
	public ModelAndView adminLogin(String name, String password,HttpServletRequest request) {
		AdminUser adminuser = adminUserService.login(name, password);
		if(adminuser==null){
			return new ModelAndView("index", "admin_error", "用户名不存在");
		}
		if(!StringUtils.equals(password, adminuser.getPassword())){
			return new ModelAndView("index", "admin_error", "用户名密码不正确");
		}
		return new ModelAndView("admin_login", "admin", adminuser);
	}

	@RequestMapping(value = "/addAdmin.do")
	public String addAdmin(String operator, String no, String name, String password, Integer right) {
		System.out.println(right + "..." + operator);
		AdminUser adminUser = adminUserService.findByAdminNo(no);
		if (adminUser == null) {
			adminUser = new AdminUser();
			adminUser.setCreatedBy(operator);
			adminUser.setCreateTime(new Date());
		}
		adminUser.setAdminNo(no);
		adminUser.setName(name);
		adminUser.setPassword(password);
		adminUser.setRights(right);
		adminUser.setType(EnumName.AdminType.NORMAL_ADMIN.getValue());
		adminUser.setUpdateTime(new Date());
		adminUser.setUpdatedBy(operator);
		adminUserService.save(adminUser);
		return "forward:adminList.do?pagesize=10&pagenumber=1&adminName="+operator;
	}

	@RequestMapping(value = "/adminList.do")
	public ModelAndView adminList(Integer pagesize, Integer pagenumber, String adminName, HttpServletRequest request) {
		int totalcount = adminUserService.list().size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (pagenumber > pagecount || pagenumber < 0) {
			throw new ExceptionMessage("页数有错");
		}
		Page<AdminUser> adminuser = adminUserService.adminList(pagenumber - 1, pagesize);
		List<AdminUser> admin = adminuser.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("adminName", adminName);
		return new ModelAndView("adminList", "admin", admin);
	}

	@RequestMapping(value = "/adminDetail.do")
	public ModelAndView detailAdmin(String adminName, Integer type,String oprator, HttpServletRequest request) {
		AdminUser adminUser = adminUserService.findByAdminNo(adminName);
		request.setAttribute("adminName", oprator);
		request.setAttribute("type", type);
		return new ModelAndView("adminDetail", "admin", adminUser);
	}
	
	@RequestMapping(value = "/backMain.do")
	public ModelAndView backMain(String adminName){
		AdminUser admin=adminUserService.findByAdminNo(adminName);
		return new ModelAndView("admin_login", "admin", admin);
	}
	
	@RequestMapping(value = "/deleteAdmin.do")
	public String delete(String operator,String adminName){
		adminUserService.deleteAdmin(adminName);
		return "forward:adminList.do?pagesize=10&pagenumber=1&adminName="+operator;
	}
	
	@RequestMapping(value = "/change_password.do")
	public ModelAndView changePassword(String operator,String name,String password,HttpServletRequest request){
		AdminUser admin=adminUserService.findByAdminNo(name);
		admin.setPassword(password);
		admin.setUpdatedBy(operator);
		admin.setUpdateTime(new Date());
		adminUserService.save(admin);
		request.setAttribute("change", "修改成功");
		return new ModelAndView("admin_login", "admin", admin);
	}
	
	@RequestMapping(value = "/goodset_list.do")
	public ModelAndView goodTypeList(Integer pagesize, Integer pagenumber, String adminName, HttpServletRequest request){
		int totalcount=goodTypeService.findAll().size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (pagenumber > pagecount || pagenumber < 0) {
			throw new ExceptionMessage("页数有错");
		}
		Page<GoodType> types = goodTypeService.findList(pagenumber - 1, pagesize);
		List<GoodType> type = types.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("adminName", adminName);
		return new ModelAndView("goodsSet", "type", type);
	}
	
	@RequestMapping(value = "/goodsetDetail.do")
	public ModelAndView goodTypeDetail(Integer id,String adminName,HttpServletRequest request){
		GoodType goodType=goodTypeService.detail(id);
		request.setAttribute("adminName", adminName);
		return new ModelAndView("goodtypeDetail", "goodType", goodType);
	}
	
	@RequestMapping(value="/update_goodtype.do")
	public ModelAndView addOrUpdateType(String adminName,Integer id,String name,Integer state,HttpServletRequest request){
		GoodType goodType=null;
		if(id==null){
			 goodType=new GoodType();
			 goodType.setState(EnumName.GoodTypeState.ON.getValue());
			 goodType=goodTypeService.fillCreate(goodType, adminName);
		}else{
			goodType=goodTypeService.detail(id);
			goodType=goodTypeService.fillUpdate(goodType, adminName);
		}
		goodType.setName(name);
		goodTypeService.save(goodType);
		int totalcount=goodTypeService.findAll().size();
		int pagecount = 0;
		int m = totalcount % 10;
		if (m > 0) {
			pagecount = totalcount / 10 + 1;
		} else {
			pagecount = totalcount / 10;
		}
		Page<GoodType> types = goodTypeService.findList(0, 10);
		List<GoodType> type = types.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(1);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("adminName", adminName);
		return new ModelAndView("goodsSet", "type", type);
	}
	
	@RequestMapping(value="/change_goodtype_state.do")
	public ModelAndView addOrUpdateType(String adminName,Integer id,Integer state,HttpServletRequest request){
		GoodType goodType=goodTypeService.detail(id);
		goodType.setState(state);
		goodTypeService.fillUpdate(goodType, adminName);
		goodTypeService.save(goodType);
		int totalcount=goodTypeService.findAll().size();
		int pagecount = 0;
		int m = totalcount % 10;
		if (m > 0) {
			pagecount = totalcount / 10 + 1;
		} else {
			pagecount = totalcount / 10;
		}
		Page<GoodType> types = goodTypeService.findList(0, 10);
		List<GoodType> type = types.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(1);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("adminName", adminName);
		return new ModelAndView("goodsSet", "type", type);
	}
}

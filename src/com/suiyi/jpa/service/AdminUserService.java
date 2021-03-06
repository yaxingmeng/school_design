package com.suiyi.jpa.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.AdminUser;
import com.suiyi.jpa.repository.AdminUserRepository;

@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository adminUserRepository;

	public AdminUser login(String name, String password) {
		AdminUser adminUser = adminUserRepository.findByAdminNo(name);
		return adminUser;
	}
	
	public AdminUser save(AdminUser adminUser){
		return adminUserRepository.save(adminUser);
	}
	
	public Page<AdminUser> adminList(Integer pagenumber,Integer pagesize){
		Pageable pageble=new PageRequest(pagenumber, pagesize);
		return adminUserRepository.findAll(pageble);
	}
	
	public List<AdminUser> list(){
		return adminUserRepository.findAll();
	}
	
	public AdminUser findByAdminNo(String name){
		return adminUserRepository.findByAdminNo(name);
	}
	
	public void deleteAdmin(String adminName){
		AdminUser admin=findByAdminNo(adminName);
		adminUserRepository.delete(admin);
	}

}

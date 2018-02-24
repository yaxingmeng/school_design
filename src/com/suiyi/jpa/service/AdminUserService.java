package com.suiyi.jpa.service;

import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.AdminUser;
import com.suiyi.jpa.repository.AdminUserRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

	@Autowired
	private AdminUserRepository adminUserRepository;

	public AdminUser login(String name, String password) {
		AdminUser adminUser = adminUserRepository.findByAdminNo(name);
		if(adminUser==null){
			throw new ExceptionMessage("用户名不存在");
		}
		if(!StringUtils.equals(password, adminUser.getPassword())){
			throw new ExceptionMessage("用户名密码不正确");
		}
		return adminUser;
	}
	
	public AdminUser add(AdminUser adminUser){
		return adminUserRepository.save(adminUser);
	}

}

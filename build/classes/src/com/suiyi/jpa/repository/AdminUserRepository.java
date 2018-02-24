package com.suiyi.jpa.repository;

import com.suiyi.jpa.bean.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminUserRepository extends JpaRepository<AdminUser,Integer> {

	AdminUser findByAdminNo();
}

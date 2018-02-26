package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.AdminUser;

public interface AdminUserRepository extends CrudRepository<AdminUser, Integer> {

	@Query(value = "select * from admin_user where admin_no=?1 or name=?1", nativeQuery = true)
	AdminUser findByAdminNo(String name);

	Page<AdminUser> findAll(Pageable page);

	List<AdminUser> findAll();
}

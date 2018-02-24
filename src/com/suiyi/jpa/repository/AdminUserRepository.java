package com.suiyi.jpa.repository;

import com.suiyi.jpa.bean.AdminUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdminUserRepository extends CrudRepository<AdminUser,Integer> {

	@Query(value="select * from admin_user where admin_no=?1 or name=?1",nativeQuery=true)
	AdminUser findByAdminNo(String name);
}

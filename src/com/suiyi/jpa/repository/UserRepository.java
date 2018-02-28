package com.suiyi.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByNickname(String name);
	
	User findByPhone(String phone);

}

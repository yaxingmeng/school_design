package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.repository.UserRepository;

@Service
public class UserService extends BasicService<User, Integer>{

	@Autowired
	private UserRepository userRepository;

	public User login(String name, String password) {
		User user = findByNickname(name);
		return user;
	}

	public User add(User user) {
		return userRepository.save(user);
	}

	public User findByNickname(String nickName) {
		return userRepository.findByNickname(nickName);
	}

	public User findByPhone(String phone) {
		return userRepository.findByPhone(phone);
	}
	
	public User findById(Integer id){
		return userRepository.findOne(id);
	}

	public User save(User user){
		return userRepository.save(user);
	}
}

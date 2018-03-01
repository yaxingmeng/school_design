package com.suiyi.jpa.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.UserLocation;
import com.suiyi.jpa.repository.UserLocationReposotory;

@Service
public class UserLocationService extends BasicService<UserLocation, Integer>{
	
	@Autowired
	private UserLocationReposotory userLocationReposotory;
	
	public UserLocation add(UserLocation userLocation,String name){
		userLocation=fillCreate(userLocation,name);
		return userLocationReposotory.save(userLocation);
	}
	
	public UserLocation findById(Integer id){
		UserLocation userLocation=userLocationReposotory.findOne(id);
		return userLocation;
	}
	
	public UserLocation save(UserLocation t){
		return userLocationReposotory.save(t);
	}
	
	public void deleteLocation(Integer id){
		userLocationReposotory.delete(id);
	}

}

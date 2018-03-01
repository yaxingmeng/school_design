package com.suiyi.jpa.service;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.suiyi.jpa.bean.BasicBean;
import com.suiyi.jpa.repository.BasicRepository;

public class BasicService<T extends BasicBean, ID extends Serializable> implements InitializingBean {
	
	private BasicRepository<T, ID> repository;

	public T fillCreate(T t,String name){
		t.setCreatedBy(name);
		t.setCreateTime(new Date());
		t.setUpdatedBy(name);
		t.setUpdateTime(new Date());
		return t;
	}
	
	public T fillUpdate(T t,String name){
		t.setUpdatedBy(name);
		t.setUpdateTime(new Date());
		return t;
	}
	
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	

}

package com.suiyi.jpa.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.suiyi.jpa.bean.BasicBean;

@NoRepositoryBean
public interface  BasicRepository<T extends BasicBean,ID extends Serializable> extends CrudRepository<T,ID>{
	
}

	

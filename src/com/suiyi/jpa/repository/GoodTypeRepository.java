package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.GoodType;

public interface GoodTypeRepository extends CrudRepository<GoodType, Integer>{
	
	List<GoodType> findAll();
	
	Page<GoodType> findAll(Pageable pageable);
	
	GoodType findByNameContaining(String name);

}

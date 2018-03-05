package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Goods;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {
	
	Page<Goods> findAll(Pageable pageble);
	
	List<Goods> findAll();
	
	List<Goods> findByNameContaining(String name);
	
	List<Goods> findByType(Integer type);

}

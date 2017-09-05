package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Goods;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {
	@Query(value = "select * from goods", nativeQuery = true)
	List<Goods> findAll();

	Goods findById(Integer id);
	
}

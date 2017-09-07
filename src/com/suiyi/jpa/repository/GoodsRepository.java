package com.suiyi.jpa.repository;


import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Goods;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {

	Goods findById(Integer id);

	
}

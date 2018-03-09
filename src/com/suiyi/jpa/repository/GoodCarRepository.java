package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.GoodCar;

public interface GoodCarRepository extends CrudRepository<GoodCar, Integer> {
	
	Page<GoodCar> findAll(Pageable pageable);
	
	List<GoodCar> findByGoodIdAndUserId(Integer goodId,Integer userId);

}

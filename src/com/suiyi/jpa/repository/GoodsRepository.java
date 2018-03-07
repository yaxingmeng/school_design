package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Goods;

public interface GoodsRepository extends CrudRepository<Goods, Integer> {
	
	Page<Goods> findAll(Pageable pageble);
	
	@Query(value = "select * from goods where name like %?1% limit ?2,10", nativeQuery = true)
	List <Goods> FindByName(String name ,Integer mount);
	
	@Query(value = "select * from goods where type=?1 limit ?2,10", nativeQuery = true)
	List<Goods> findByType(Integer type,Integer mount);
	
	@Query(value = "select * from goods where name like %?1% and type=?2 limit ?3,10", nativeQuery = true)
	List<Goods> findByNameAndType(String name,Integer type,Integer mount);
	
	List<Goods> findAll();
	
	List<Goods> findByNameContaining(String name);
	
	List<Goods> findByType(Integer type);
	
	List<Goods> findByName(String key);
	
	List<Goods> findByGoodNo(String no);

}

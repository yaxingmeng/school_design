package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.InStock;

public interface InRepository extends CrudRepository<InStock, Integer> {

	List<InStock> findByProvideId(Integer id);

	List<InStock> findByGoodsId(Integer id);

	List<InStock> findByIntimeBetween(Date start2, Date end2);

}

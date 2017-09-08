package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.InStock;

public interface InRepository extends CrudRepository<InStock, Integer> {

	List<InStock> findByProvideId(Integer id);

	List<InStock> findByGoodsId(Integer id);

	Page<InStock> findByIntimeBetweenOrderByIntime(Date start2, Date end2,Pageable page);


}

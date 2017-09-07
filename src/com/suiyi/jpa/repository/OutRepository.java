package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.OutStock;

public interface OutRepository extends CrudRepository<OutStock, Integer> {

	List<OutStock> findBySaleId(Integer id);

	List<OutStock> findByGoodsId(Integer id);

	List<OutStock> findByOuttimeBetween(Date start, Date end);
}

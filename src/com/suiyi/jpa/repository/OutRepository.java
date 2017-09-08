package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.suiyi.jpa.bean.OutStock;

public interface OutRepository extends JpaRepository<OutStock, Integer> {

	List<OutStock> findBySaleId(Integer id);

	List<OutStock> findByGoodsId(Integer id);

	Page<OutStock> findByOuttimeBetweenOrderByOuttime(Date start, Date end,Pageable page);
}

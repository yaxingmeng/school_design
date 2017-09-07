package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Sale;

public interface SaleRepository extends CrudRepository<Sale, Integer> {
	/**
	 * 获取所有供应商的信息
	 */
	List<Sale> findAll();
	/**
	 * 根据id获取供应商
	 */
	Sale findById(Integer id);
}

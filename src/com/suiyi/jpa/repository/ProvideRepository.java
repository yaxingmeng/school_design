package com.suiyi.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suiyi.jpa.bean.Provide;

public interface ProvideRepository extends JpaRepository<Provide, Integer> {
	/**
	 * 获取所有供应商的信息
	 */
	List<Provide> findAll();
	/**
	 * 根据id获取供应商
	 */
	Provide findById(Integer id);
	/**
	 * 根据name获取供应商
	 */
	Provide findByName(String name);
}

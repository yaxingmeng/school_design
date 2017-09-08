package com.suiyi.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.InStock;
import com.suiyi.jpa.repository.InRepository;

@Service
public class InService {
	@Autowired
	private InRepository inRepository;

	/**
	 * 添加入库信息
	 */
	public void addIn(InStock instock) {
		inRepository.save(instock);
	}

	/**
	 * 获取供应商的入库记录
	 */
	public List<InStock> findProvideStock(Integer id) {
		return inRepository.findByProvideId(id);
	}

	/**
	 * 获取商品的入库记录
	 */
	public List<InStock> findgoodsStock(Integer id) {
		return inRepository.findByGoodsId(id);
	}

	/**
	 * 获取某段时间内的入库信息
	 */

	public Page<InStock> findByIntimeBetween(Date start2, Date end2) {
		Page<InStock> instocks = inRepository.findByIntimeBetweenOrderByIntime(start2, end2,new PageRequest(1,10));
		return instocks;

	}
}

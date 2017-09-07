package com.suiyi.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.InStock;
import com.suiyi.jpa.bean.OutStock;
import com.suiyi.jpa.repository.OutRepository;

@Service
public class OutService {
	@Autowired
	private OutRepository outRepository;
	/**
	 * 添加出库信息
	 */
	public void addOut(OutStock outStock){
		outRepository.save(outStock);
	}
	
	/**
	 * 获取销售商的出库信息
	 */
	public List<OutStock> findSaleStock(Integer id){
		return outRepository.findBySaleId(id);
	}
	/**
	 * 获取商品的出库记录
	 */
	public List<OutStock> findGoodsStock(Integer id){
		return outRepository.findByGoodsId(id);
	}
	
	/**
	 * 获取某段时间内的出库信息
	 */
	
	public List<OutStock> findByOuttimeBetween(Date start,Date end){
		return outRepository.findByOuttimeBetween(start,end);
	}
}

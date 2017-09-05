package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	public Stock queryStack(Integer goods){
		return stockRepository.findByGoods(goods);
	}

}

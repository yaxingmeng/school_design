package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.bean.Stocks;

public interface StockRepository extends CrudRepository<Stock, Integer> {
	Stock findByGoodsId(Integer goods);
	
}

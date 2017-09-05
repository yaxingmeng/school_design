package com.suiyi.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Stock;

public interface StockRepository extends CrudRepository<Stock, Integer> {
 Stock findByGoods(Integer goods);
}
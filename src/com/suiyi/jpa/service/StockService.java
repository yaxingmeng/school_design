package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	/**
	 * 根据商品编号获取库存
	 * @param goods
	 * @return
	 */
	public Stock getStockById(Integer goods){
		return stockRepository.findByGoodsId(goods);
	}
	/**
	 * 入库后添加库存数量
	 */
	public void addStockAmount(Integer goods,Integer amount){
		Stock stock=stockRepository.findByGoodsId(goods);
		Integer a=stock.getAmount();
		stock.setAmount(amount+a);
		stockRepository.save(stock);
	}
	/**
	 * 出库后减少库存数量
	 */
	public void subStockAmount(Integer goods,Integer amount){
		Integer a=stockRepository.findByGoodsId(goods).getAmount();
		Stock stock=stockRepository.findByGoodsId(goods);
		stock.setAmount(a-amount);
		stockRepository.save(stock);
	}
/**
 * 添加一个商品的库存
 */
	public void addStock(Stock stock){
		stockRepository.save(stock);
	}
	
	
}

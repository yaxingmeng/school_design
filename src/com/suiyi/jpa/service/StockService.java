package com.suiyi.jpa.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.InStock;
import com.suiyi.jpa.bean.OutStock;
import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.bean.Sale;
import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.bean.Stocks;
import com.suiyi.jpa.repository.StacksRepository;
import com.suiyi.jpa.repository.StockRepository;

@Service
public class StockService {
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProvideService provideService;
	@Autowired
	private InService inService;
	@Autowired
	private StockService stockService;
	@Autowired
	private OutService outService;
	@Autowired
	private SaleService saleService;
	@Autowired
	private StacksRepository stocksRepository;
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
	@Transactional
	public void addStockAmount(Integer provideId, Integer goodsId, Integer amount){
		Goods goods = goodsService.findById(goodsId);
		Stock stock=stockRepository.findByGoodsId(goodsId);
		Provide provide = provideService.findById(provideId);
		InStock in = new InStock();
		in.setProvide(provide);
		in.setGoods(goods);
		in.setAmount(amount);
		Date date = new Date();
		in.setIntime(date);
		inService.addIn(in);
		Integer a=stock.getAmount();
		stock.setAmount(amount+a);
		stockRepository.save(stock);
	}
	/**
	 * 出库后减少库存数量
	 */
	@Transactional
	public void subStockAmount(Integer saleId, Integer goodsId, Integer amount){
		Goods goods = goodsService.findById(goodsId);
		Sale sale = saleService.findById(saleId);
		OutStock out = new OutStock();
		out.setSale(sale);
		out.setGoods(goods);
		out.setAmount(amount);
		Date date = new Date();
		out.setOuttime(date);
		outService.addOut(out);
		Integer a=stockRepository.findByGoodsId(goodsId).getAmount();
		Stock stock=stockRepository.findByGoodsId(goodsId);
		stock.setAmount(a-amount);
		stockRepository.save(stock);
	}
/**
 * 添加一个商品的库存
 */
	public void addStock(Stock stock){
		stockRepository.save(stock);
	}
	
	public List<Stocks> findAllStocks(Date start1,Date end1,Date start2,Date end2){
		return stocksRepository.findAllStocks(start1, end1,start2,end2);
	}
	
}

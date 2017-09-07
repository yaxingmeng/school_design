package com.suiyi.jpa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.InStock;
import com.suiyi.jpa.bean.OutStock;
import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.bean.Sale;
import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.InService;
import com.suiyi.jpa.service.OutService;
import com.suiyi.jpa.service.ProvideService;
import com.suiyi.jpa.service.SaleService;
import com.suiyi.jpa.service.StockService;

@Controller
@RequestMapping
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProvideService provideService;
	@Autowired
	private InService inservice;
	@Autowired
	private SaleService saleservice;
	@Autowired
	private OutService outService;

	/**
	 * 产看每个产品的库存
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryStock.do")
	public ModelAndView queryStock(Integer id) {
		System.out.println(id);
		Stock stock = stockService.getStockById(id);
		return new ModelAndView("detail", "stock", stock);
	}

	/**
	 * 添加库存信息和库存量
	 * 
	 * @param provideId
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	@Transactional
	@RequestMapping(value = "/addStock.do")
	public ModelAndView addStock(Integer provideId, Integer goodsId, Integer amount) {
		Goods goods = goodsService.findById(goodsId);
		Provide provide = provideService.findById(provideId);
		if (goods == null || provide == null) {
			return new ModelAndView("error", "error", "商品或供应商不存在");
		}
		InStock in = new InStock();
		in.setProvide(provide);
		in.setGoods(goods);
		in.setAmount(amount);
		Date date = new Date();
		in.setIntime(date);
		inservice.addIn(in);
		stockService.addStockAmount(goodsId, amount);
		return new ModelAndView("forward:getAll.do", null);

	}

	/**
	 * 销售商品减少库存
	 */
	@Transactional
	@RequestMapping(value = "/outStock.do")
	public ModelAndView outStock(Integer saleId, Integer goodsId, Integer amount) {
		Stock s = stockService.getStockById(goodsId);
		int a = s.getAmount();
		if (amount > a) {
			return new ModelAndView("error", "error", "超出库存啦！！！");
		}
		Goods goods = goodsService.findById(goodsId);
		Sale sale = saleservice.findById(saleId);
		OutStock out = new OutStock();
		out.setSale(sale);
		out.setGoods(goods);
		out.setAmount(amount);
		Date date = new Date();
		out.setOuttime(date);
		outService.addOut(out);
		stockService.subStockAmount(goodsId, amount);
		return new ModelAndView("forward:getAll.do", null);
	}

	/**
	 * 查询供应商的入库记录
	 */
	@RequestMapping(value = "/queryProvideStock.do")
	public ModelAndView queryProvideStock(Integer pid) {
		List<InStock> instocks = inservice.findProvideStock(pid);
		return new ModelAndView("provideStock", "instocks", instocks);
	}

	/*
	 * 查询销售商的出库记录
	 * 
	 */
	@RequestMapping(value = "/querySaleStock.do")
	public ModelAndView querySaleStock(Integer sid) {
		List<OutStock> outStocks = outService.findSaleStock(sid);
		return new ModelAndView("saleStock", "outstocks", outStocks);

	}

	/**
	 * 查询商品的入库记录
	 */
	@RequestMapping(value = "/goodsInStock.do")
	public ModelAndView goodsInStock(Integer id) {
		List<InStock> instocks = inservice.findgoodsStock(id);
		return new ModelAndView("goodsInStock", "instocks", instocks);
	}

	/**
	 * 查询商品的出库记录
	 */
	@RequestMapping(value = "/goodsOutStock.do")
	public ModelAndView goodsOutStock(Integer id) {
		List<OutStock> outStocks = outService.findGoodsStock(id);
		return new ModelAndView("goodsOutStock", "outstocks", outStocks);
	}

	/**
	 * 查询某段时间内的入库信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/timeInStock.do")
	public ModelAndView timeInStock(String start, String end) throws Exception {
		Date start2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(start);
		Date end2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(end);
		List<InStock> instocks = inservice.findByIntimeBetween(start2, end2);
		return new ModelAndView("timeInStock", "instocks", instocks);
	}
	/**
	 * 查询某段时间内的出库信息
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="/timeOutStock.do")
	public ModelAndView timeOutStock(String start, String end) throws Exception {
		Date start2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(start);
		Date end2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(end);
		List<OutStock> outstocks = outService.findByOuttimeBetween(start2, end2);
		return new ModelAndView("timeOutStock", "outstocks", outstocks);
	}
}

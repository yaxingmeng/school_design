package com.suiyi.jpa.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.StockService;

@Controller
@RequestMapping
public class GoodsController {
	@Autowired
	private GoodsService goodService;
	@Autowired
	private StockService stockService;
	/**
	 * 获取所有商品信息
	 * @return
	 */
	@RequestMapping(value ="/getAll.do")
	public ModelAndView getAll()
	{ 
		List<Goods> goods=goodService.getAll();
		return new ModelAndView("goods","goods",goods);
		
	}
	/**
	 * 增加商品
	 */
	@RequestMapping(value="/addGoods.do")
	public ModelAndView addGoods(String name){
		Goods goods=goodService.addGoods(name);
		Stock stock=new Stock();
		stock.setGoods(goods);
		stock.setAmount(0);
		stockService.addStock(stock);
		return new ModelAndView("forward:getAll.do",null);
		
	}
/**
 * 获取所需修改的商品的信息
 */
	@RequestMapping(value="/updateQuery.do")
	public ModelAndView updateQuery(Integer id){
		Goods goods=goodService.updateQuery(id);
		return new ModelAndView("updateGoods","goods",goods);
	}
	/**
	 * 修改商品信息
	 */
	@RequestMapping(value="/updateGoods.do")
	public ModelAndView updateQuery(Integer id,String name){
		goodService.updateGoods(id, name);
		return new ModelAndView("forward:getAll.do",null);
		
	}
	
	/**
	 * 修改商品状态
	 */
	@RequestMapping(value="/changeState.do")
	public ModelAndView changeState(Integer id,Integer state){
		Goods goods=goodService.findById(id);
		goodService.changeState(goods, state);
		return new ModelAndView("forward:getAll.do",null);
	}
}

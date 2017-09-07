package com.suiyi.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.bean.Sale;
import com.suiyi.jpa.service.SaleService;

@Controller
@RequestMapping
public class SaleController {
	@Autowired
	private SaleService saleService;
	@RequestMapping(value="/addSale.do")
	public ModelAndView getSale(String sname){
		saleService.addSale(sname);
		return new ModelAndView("forward:getSales.do",null);
	}
	@RequestMapping(value="/getSales.do")
	public ModelAndView getSales(){
		List<Sale> sales=saleService.findAll();
		return new ModelAndView("sales","sales",sales);
	}
	/**
	 * 进入出库页面
	 * @param request
	 * @param goodsId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value="/getSale.do")
	public ModelAndView getSale( HttpServletRequest request,String goodsId,String goodsName,String Amount){
		List<String> goods=new ArrayList<>();
		goods.add(goodsId);
		goods.add(goodsName);
		goods.add(Amount);
		request.setAttribute("goods", goods);
		List<Sale> sales=saleService.findAll();
		return new ModelAndView("outStock","sales",sales);
	}
	/**
	 * 获取所需修改销售商的信息
	 */
	@RequestMapping(value="/getUpdateSale.do")
	public ModelAndView getUpdateProvide(Integer sid){
		Sale sale=saleService.findById(sid);
		return new ModelAndView("updateSale","sale",sale);
		
	}
	/**
	 * 修改销售商信息
	 */
	
	@RequestMapping(value="/updateSale.do")
	public ModelAndView updateSale(Integer id,String name){
		Sale sale=saleService.findById(id);
		sale.setName(name);
		saleService.updateSale(sale);
		return new ModelAndView("forward:getSales.do",null);
	}
	
	/**
	 * 删除销售商
	 */
	@RequestMapping(value="/deleteSale.do")
	public ModelAndView deleteSale(Integer sid){
		saleService.deleteSale(sid);
		return new ModelAndView("forward:getSales.do",null);
	}
}

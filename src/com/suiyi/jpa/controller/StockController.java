package com.suiyi.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.service.StockService;

@Controller
@RequestMapping
public class StockController {
private StockService stockService;
@RequestMapping(value="/queryStock.do")
public ModelAndView queryStock(Integer id){
	Stock stock=stockService.queryStack(id);
	return new ModelAndView("detail","stock",stock);
}
}

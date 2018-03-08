package com.suiyi.jpa.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.GoodCar;
import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.service.GoodCarService;
import com.suiyi.jpa.service.GoodTypeService;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.UserService;

@Controller
@RequestMapping
public class GoodCarController {
	
	@Autowired
	private GoodCarService goodCarService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodTypeService goodTypeService;

	@RequestMapping(value = "/add_goodcar.do")
	public ModelAndView addGoodCar(Integer goodId,String userName,Integer amount,
			Integer pagenumber,String name,Integer goodtype, HttpServletRequest request){
		GoodCar goodCar=new GoodCar();
		goodCar.setGoodId(goodId);
		User user=userService.findByNickname(userName);
		goodCar.setUserId(user.getId());
		goodCar.setGoodId(goodId);
		goodCar.setAmount(amount);
		goodCarService.fillCreate(goodCar, userName);
		goodCarService.save(goodCar);
		int totalcount = goodsService.findAll().size();
		int pagecount = 0;
		int pagesize=10;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (pagenumber > pagecount || pagenumber < 0) {
			throw new ExceptionMessage("页数有错");
		}
		List<Goods> goods=null;
		if(name!=null||goodtype!=null){
			goods=goodsService.findNameOrType(name, goodtype, pagenumber-1, pagesize);
		}else{
			Page<Goods> good = goodsService.findList(pagenumber - 1, pagesize);
			goods = good.getContent();
		}
		List<Integer> page = new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("type",3);
		request.setAttribute("userName", userName);
		request.setAttribute("name",name);
		request.setAttribute("goodtype",goodtype);
		List<GoodType> goodTypes = goodTypeService.findAll();
		request.setAttribute("goodType", goodTypes);
		return new ModelAndView("user_login", "goods", goods);
	}
	
}

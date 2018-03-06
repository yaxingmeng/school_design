package com.suiyi.jpa.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.service.GoodTypeService;
import com.suiyi.jpa.service.GoodsService;

@Controller
@RequestMapping
public class GoodsController {

	@Autowired
	private GoodsService goodsService;

	@Autowired
	private GoodTypeService goodTypeService;

	@RequestMapping(value = "/good_list.do")
	public ModelAndView findAll(Integer type, String adminName, Integer pagesize, Integer pagenumber,
			HttpServletRequest request) {
		int totalcount = goodsService.findAll().size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (pagenumber > pagecount || pagenumber < 0) {
			throw new ExceptionMessage("页数有错");
		}
		Page<Goods> good = goodsService.findList(pagenumber - 1, pagesize);
		List<Goods> goods = good.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		if (type == 0) {
			request.setAttribute("adminName", adminName);
			return new ModelAndView("goodsAdmin", "goods", goods);
		}
		return new ModelAndView("user_login", "goods", goods);
	}

	@RequestMapping(value = "/goodtype_list.do")
	public ModelAndView goodTypeList(HttpServletRequest request, String adminName, Integer type) {
		List<GoodType> goodTypes = goodTypeService.findAll();
		request.setAttribute("adminName", adminName);
		request.setAttribute("type", type);
		return new ModelAndView("addGoods", "goodType", goodTypes);
	}

	@RequestMapping(value = "/add_good.do")
	public String addGood(String unit,Integer id, String name, String goodNo, Double price, Integer amount, Integer type,
			String adminName, HttpServletRequest request) {
		Goods good = null;
		if (id == null) {
			boolean charge = goodsService.findByNameOrNO(name, goodNo);
			if (!charge) {
				request.setAttribute("exception", "商品名称或商品编号已存在");
				request.setAttribute("adminName", adminName);
				request.setAttribute("type", 0);
				List<GoodType> goodTypes = goodTypeService.findAll();
				request.setAttribute("goodType", goodTypes);
				return "addGoods";
			}
			good = new Goods();
			goodsService.fillCreate(good, adminName);
		} else {
			good=goodsService.findById(id);
			goodsService.fillUpdate(good, adminName);
		}
		good.setUnit(unit);
		good.setGoodNo(goodNo);
		good.setName(name);
		good.setType(type);
		good.setPrice(price);
		good.setAmount(amount);
		good.setState(EnumName.GoodState.ON_SALE.getValue());
		GoodType goodType = goodTypeService.detail(type);
		good.setGoodType(goodType);
		goodsService.save(good);
		return "forward:good_list.do?pagesize=10&pagenumber=1&type=0";
	}

	@RequestMapping(value = "/good_detail.do")
	public String goodDetail(String adminName, Integer id, HttpServletRequest request) {
		List<GoodType> goodTypes = goodTypeService.findAll();
		request.setAttribute("goodType", goodTypes);
		Goods goods = goodsService.findById(id);
		request.setAttribute("good", goods);
		request.setAttribute("adminName", adminName);
		return "addGoods";
	}
	
	@RequestMapping(value = "/good_change_state.do")
	public String changeState(Integer id,Integer state,String adminName,Integer pageNumber){
		Goods goods=goodsService.findById(id);
		goods.setState(state);
		goodsService.fillUpdate(goods, adminName);
		goodsService.save(goods);
		return "forward:good_list.do?pagesize=10&pagenumber="+pageNumber+"&type=0";
	}
	
	@RequestMapping(value = "/good_list_user.do")
	public ModelAndView findList(String name,Integer goodtype,String userName,Integer pagesize, Integer pagenumber,
			HttpServletRequest request) {
		int totalcount = goodsService.findAll().size();
		int pagecount = 0;
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

package com.suiyi.jpa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.service.ProvideService;

@Controller
@RequestMapping
public class ProvideController {
	@Autowired
	private ProvideService provideService;
	@RequestMapping(value="/addProvide.do")
	public ModelAndView addProvide(String pname){
		provideService.addProvide(pname);
		return new ModelAndView("forward:getProvides.do",null);
	}
	@RequestMapping(value="/getProvides.do")
	public ModelAndView getProvides(){
		List<Provide> provides=provideService.findAll();
		return new ModelAndView("provides","provides",provides);
	}
	/**
	 * 进入入库页面
	 * @param request
	 * @param goodsId
	 * @param goodsName
	 * @return
	 */
	@RequestMapping(value="/getProvide.do")
	public ModelAndView getProvide( HttpServletRequest request,String goodsId,String goodsName){
		System.out.println(goodsId+":"+goodsName);
		List<String> goods=new ArrayList<>();
		goods.add(goodsId);
		goods.add(goodsName);
		request.setAttribute("goods", goods);
		List<Provide> provides=provideService.findAll();
		return new ModelAndView("inStock","provides",provides);
	}
	
	/**
	 * 获取所需修改供应商的信息
	 */
	@RequestMapping(value="/getUpdateProvide.do")
	public ModelAndView getUpdateProvide(Integer pid){
		Provide provide=provideService.findById(pid);
		return new ModelAndView("updateProvide","provide",provide);
		
	}
	/**
	 * 修改供应商信息
	 */
	
	@RequestMapping(value="/updateProvide.do")
	public ModelAndView updateProvide(Integer pid,String pname){
		provideService.updateProvide(pid,pname);
		return new ModelAndView("forward:getProvides.do",null);
	}
	
	/**
	 * 删除供应商
	 */
	@RequestMapping(value="/deleteProvide.do")
	public ModelAndView deleteProvide(Integer pid,Integer state){
		provideService.deleteProvide(pid,state);
		return new ModelAndView("forward:getProvides.do",null);
	}
	
	
}

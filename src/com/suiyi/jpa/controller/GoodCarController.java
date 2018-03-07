package com.suiyi.jpa.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.suiyi.jpa.bean.GoodCar;
import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.service.GoodCarService;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.UserService;

@Controller
@RequestMapping
public class GoodCarController {
	
	@Autowired
	private GoodCarService goodCarService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/add_goodcar.do")
	public String addGoodCar(Integer goodId,String userName,Integer amount,
			Integer pagenumber,String name,Integer goodtype, HttpServletRequest request){
		GoodCar goodCar=new GoodCar();
		goodCar.setGoodId(goodId);
		User user=userService.findByNickname(userName);
		goodCar.setUserId(user.getId());
		goodCar.setAmount(amount);
		goodCarService.fillCreate(goodCar, userName);
		goodCarService.save(goodCar);
		return "forward:good_list_user.do?pagesize=10&pagenumber="+pagenumber+"&userName="+userName
				+"&type=0&name="+name+"&goodtype="+goodtype;
	}
	
}

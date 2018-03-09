package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.suiyi.jpa.bean.Orders;

public class OrderService extends BasicService<Orders, Integer> {
	
	@Autowired
	private OrderService orderService;

}

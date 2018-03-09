package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.suiyi.jpa.bean.OrderItem;
import com.suiyi.jpa.repository.OrderItemRepository;

public class OrderItemService extends BasicService<OrderItem, Integer> {

	@Autowired
	private OrderItemRepository orderItemRepository;
}

package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.suiyi.jpa.bean.OrderItem;
import com.suiyi.jpa.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService extends BasicService<OrderItem, Integer> {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public OrderItem save(OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

    public List<OrderItem> findByOrderId(Integer id) {
        return orderItemRepository.findByOrderId(id);
    }
}

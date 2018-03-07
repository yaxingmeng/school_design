package com.suiyi.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

}

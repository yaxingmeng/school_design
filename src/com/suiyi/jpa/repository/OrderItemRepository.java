package com.suiyi.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.OrderItem;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(Integer id);

}

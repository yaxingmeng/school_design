package com.suiyi.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

}

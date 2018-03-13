package com.suiyi.jpa.repository;

import com.suiyi.jpa.bean.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    Page<Orders> findAll(Pageable pageable);

    List<Orders> findAll();

    Page<Orders> findByOrderNoOrUserNickname(String nameOrNo,Pageable pageable);

    Page<Orders> findByCreateTime(Date time,Pageable pageable);

  //  List<Orders> findByOrderNoOrUserNicknameAndCreateTime(String nameOrNo,Date time);

}

package com.suiyi.jpa.repository;

import com.suiyi.jpa.bean.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface OrdersRepository extends CrudRepository<Orders, Integer> {

    Page<Orders> findAll(Pageable pageable);

    List<Orders> findAll();

    Page<Orders> findByOrderNoOrUserNickname(String nameOrNo,Pageable pageable);

    Page<Orders> findByCreateTimeBetween(Date start,Date end,Pageable pageable);

  @Query(value = "select * from orders o LEFT JOIN user u on o.user_id=u.id  " +
          "where  o.order_no like %?1% or u.nickname like %a% and o.create_time<=?2 and o.creaye_time>?3  limit ?4,10",nativeQuery = true)
    List<Orders> findByOrderNoOrUserNicknameAndCreateTime(String nameOrNo,Date end,Date start,Integer mount);

}

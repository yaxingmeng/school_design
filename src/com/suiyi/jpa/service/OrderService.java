package com.suiyi.jpa.service;

import com.suiyi.jpa.bean.Orders;
import com.suiyi.jpa.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService extends BasicService<Orders, Integer> {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrdersRepository ordersRepository;

    public Page<Orders> allPage(Integer pagesize, Integer pagenumber) {
        Pageable pageable = new PageRequest(pagenumber, pagesize);
        return ordersRepository.findAll(pageable);
    }

    public List<Orders> allList() {
        return ordersRepository.findAll();
    }

    public Page<Orders> findByNameOrNo(Integer pagesize, Integer pagenumber,String nameOrNo) {
        Pageable pageable = new PageRequest(pagenumber, pagesize);
        return ordersRepository.findByOrderNoOrUserNickname(nameOrNo,pageable);
    }

    public Page<Orders> findByCreateTime(Integer pagesize, Integer pagenumber,Date start,Date end) {
        Pageable pageable = new PageRequest(pagenumber, pagesize);
        return ordersRepository.findByCreateTimeBetween(start,end,pageable);
    }

   public List<Orders> findByNameOrNoAndCreateTime(String nameOrNo,Date start,Date end,Integer pagenumber){
        Integer mount=10*pagenumber;
        return ordersRepository.findByOrderNoOrUserNicknameAndCreateTime(nameOrNo,end,start,mount);
    }

    public Orders detail(Integer id){
       return ordersRepository.findOne(id);
    }

    public Orders save(Orders orders){
        ordersRepository.save(orders);
        return orders;
    }
}

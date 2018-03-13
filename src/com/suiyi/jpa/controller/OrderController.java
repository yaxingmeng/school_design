package com.suiyi.jpa.controller;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.OrderItemService;
import com.suiyi.jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private GoodsService  goodsService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/add_order.do")
    public String balance(Integer id, Integer mount) {
        Goods goods=goodsService.findById(id);

        return null;
    }

   /* public String list(Integer pagenumber,String nameOrNo,String time){

    }*/
}

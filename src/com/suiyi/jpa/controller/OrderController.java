package com.suiyi.jpa.controller;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.Orders;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.OrderItemService;
import com.suiyi.jpa.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    /**
     * 商品页面添加订单
     *
     * @param id
     * @param mount
     * @return
     */
    @RequestMapping(value = "/add_order.do")
    public String balance(Integer id, Integer mount) {
        Goods goods = goodsService.findById(id);

        return null;
    }

    @RequestMapping(value = "/order_list.do")
    public String list(Integer pagenumber, String nameOrNo, Date start, Date end, String adminName, HttpServletRequest request) {
        int totalcount = orderService.allList().size();
        int pagecount = 0;
        int m = totalcount % 10;
        if (m > 0) {
            pagecount = totalcount / 10 + 1;
        } else {
            pagecount = totalcount / 10;
        }
        if (pagenumber > pagecount || pagenumber < 0) {
            throw new ExceptionMessage("页数有错");
        }
        List<Orders> order = null;
        if (StringUtils.isNotEmpty(nameOrNo)) {
            if (start != null || end != null) {
                order = orderService.findByNameOrNoAndCreateTime(nameOrNo, start, end, pagenumber - 1);
            } else {
                order = orderService.findByNameOrNo(10, pagenumber - 1, nameOrNo).getContent();
            }
        } else if (start != null || end != null) {
            order = orderService.findByCreateTime(10, pagenumber - 1, start, end).getContent();
        } else {
            order = orderService.allPage(10, pagenumber - 1).getContent();
        }
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("adminName", adminName);
        request.setAttribute("page", page);
        request.setAttribute("order", order);
        return "orderAdmin";
    }

    @RequestMapping(value = "/order_findone.do")
    public String findOne(Integer id, String adminName, Integer type, HttpServletRequest request) {
        Orders order = orderService.detail(id);
        request.setAttribute("type", type);
        request.setAttribute("adminName", adminName);
        request.setAttribute("order", order);
        Map<Integer, String> payState = new HashMap<>();
        for (int i = 0; i < EnumName.PaySate.values().length; i++) {
            payState.put(i, EnumName.PaySate.getCaptionByValue(i));
        }
        Map<Integer, String> tranState = new HashMap<>();
        for (int i = 0; i < EnumName.TranState.values().length; i++) {
            tranState.put(i, EnumName.TranState.getCaptionByValue(i));
        }
        request.setAttribute("payState", payState);
        request.setAttribute("tranState", tranState);
        return "orderDetail";
    }

    @RequestMapping(value = "/order_update.do")
    public String updateOrder(Integer id, String adminName, Integer payState, Integer tranState, String phone, String contact, String location) {
        Orders order = orderService.detail(id);
        order.setPayState(payState);
        order.setTranState(tranState);
        order.setPhone(phone);
        order.setContact(contact);
        order.setLocation(location);
        orderService.fillUpdate(order,adminName);
        orderService.save(order);
        return "forward:order_list.do?pagenumber=1";
    }


}

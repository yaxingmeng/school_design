package com.suiyi.jpa.controller;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.*;
import com.suiyi.jpa.service.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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

    @Autowired
    private UserLocationService userLocationService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodCarService goodCarService;

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
    public String list(Integer pagenumber, String nameOrNo, @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss") Date start, @DateTimeFormat(pattern = "yyyy-MM-dd HH:ss") Date end, String adminName, HttpServletRequest request) {
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
        if (end == null) {
            end = new Date();
        }
        List<Orders> order = null;
        if (StringUtils.isNotEmpty(nameOrNo) && start != null) {
            order = orderService.findByNameOrNoAndCreateTime(nameOrNo, start, end, pagenumber - 1);
        } else {
            order = orderService.findCharge(nameOrNo, start, end, pagenumber - 1);
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
        orderService.fillUpdate(order, adminName);
        orderService.save(order);
        return "forward:order_list.do?pagenumber=1";
    }

    @Transactional
    @RequestMapping(value = "/order_add_good.do")
    public String addFromGoods(Integer goodId, Integer payState, Integer locationId, Integer mount, String userName, HttpServletRequest request) {
        Goods goods = goodsService.findById(goodId);
        if (mount <= goods.getAmount()) {
            goods.setAmount(goods.getAmount() - mount);
            goodsService.save(goods);
        } else {
            throw new ExceptionMessage("超出库存");
        }
        Orders orders = new Orders();
        User user = userService.findByNickname(userName);
        UserLocation userLocation = userLocationService.findById(locationId);
        orders.setLocation(userLocation.getLocation());
        orders.setContact(userLocation.getConnector());
        orders.setPhone(userLocation.getPhone());
        orders.setTranState(EnumName.TranState.UNDILIVERY.getValue());
        orders.setPayState(EnumName.PaySate.getOf(payState).getValue());
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        orders.setOrderNo(sdf.format(time) + user.getId());
        orders.setUser(user);
        orders.setUserId(user.getId());
        orders = orderService.fillCreate(orders, userName);
        orderService.save(orders);
        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(mount);
        orderItem.setGoodId(goodId);
        orderItem.setGoods(goods);
        orderItem.setUserId(user.getId());
        orderItem.setUser(user);
        orderItem.setOrderId(orders.getId());
        orderItem = orderItemService.fillCreate(orderItem, userName);
        orderItemService.save(orderItem);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        orderService.save(orders);
        request.setAttribute("userName", userName);
        return "forward:good_list_user.do?pagesize=10&pagenumber=1&type=3";
    }

    @RequestMapping(value = "/order_list_user.do")
    public String orderListUser(Integer pagenumber, String userName, HttpServletRequest request) {
        User user = userService.findByNickname(userName);
        int totalcount = orderService.findUserList(user.getId()).size();
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
        List<Orders> orders = orderService.findListUser(pagenumber - 1, user.getId());
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("page", page);
        request.setAttribute("userName", userName);
        request.setAttribute("order", orders);
        request.setAttribute("type", 4);
        return "user_login";
    }

    @RequestMapping(value = "/order_user_change.do")
    public String changeState(Integer pagenumber, Integer orderId, Integer state, String userName, HttpServletRequest request) {
        Orders orders = orderService.detail(orderId);
        if (state == 0) {
            orders.setPayState(EnumName.PaySate.PAY.getValue());

        } else {
            orders.setTranState(EnumName.TranState.RECEIVED.getValue());
        }
        orderService.fillUpdate(orders, userName);
        orderService.save(orders);
        return "forward:order_list_user.do?pagenumber=" + pagenumber;
    }

    @RequestMapping(value = "/order_findone_user.do")
    public String findOneUser(Integer id, String userName, HttpServletRequest request) {
        Orders order = orderService.detail(id);
        request.setAttribute("userName", userName);
        request.setAttribute("order", order);
        request.setAttribute("operate", 1);
        request.setAttribute("type", 4);
        return "user_login";
    }

    @RequestMapping(value = "/order_car_add.do")
    public String addOrderFromCar(Integer[] ck1, String userName, Integer payState, Integer locationId, HttpServletRequest request) {
        for (int i = 0; i < ck1.length; i++) {
            GoodCar goodCar = goodCarService.detail(ck1[i]);
            if (goodCar.getAmount() > goodCar.getGoods().getAmount()) {
                throw new ExceptionMessage(goodCar.getGoods().getName() + "超出库存");
            }
        }
        UserLocation userLocation = userLocationService.findById(locationId);
        User user = userService.findByNickname(userName);
        Orders orders = new Orders();
        orders.setPayState(payState);
        orders.setTranState(EnumName.TranState.UNDILIVERY.getValue());
        orders.setUserId(user.getId());
        orders.setUser(user);
        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        orders.setOrderNo(sdf.format(time) + user.getId());
        orders.setPhone(userLocation.getPhone());
        orders.setContact(userLocation.getConnector());
        orders.setLocation(userLocation.getLocation());
        orderService.fillCreate(orders,userName);
        orderService.save(orders);
        List<OrderItem> orderItemList=new ArrayList<>();
        for (int i = 0; i < ck1.length; i++) {
            GoodCar goodCar = goodCarService.detail(ck1[i]);
            OrderItem orderItem=new OrderItem();
            orderItem.setOrderId(orders.getId());
            orderItem.setUser(user);
            orderItem.setUserId(user.getId());
            orderItem.setGoods(goodCar.getGoods());
            orderItem.setAmount(goodCar.getAmount());
            orderItemService.fillCreate(orderItem,userName);
            orderItemService.save(orderItem);
            orderItemList.add(orderItem);
        }
        orders.setOrderItem(orderItemList);
        orderService.save(orders);
        request.setAttribute("userName", userName);
        request.setAttribute("type", 2);
        return "user_login";
    }

    @RequestMapping(value = "/order_car_detail.do")
    public String orderCarList(Integer[] ck1, String userName, HttpServletRequest request) {
        List<GoodCar> goodCars = new ArrayList<>();
        Double totalPrice = 0.0;
        for (int i = 0; i < ck1.length; i++) {
            GoodCar goodCar = goodCarService.detail(ck1[i]);
            goodCars.add(goodCar);
            totalPrice = totalPrice + goodCar.getGoods().getPrice() * goodCar.getAmount();
        }
        User user = userService.findByNickname(userName);
        request.setAttribute("userName",userName);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("user", user);
        request.setAttribute("goodCar", goodCars);
        request.setAttribute("ck1", ck1);
        request.setAttribute("type", 2);
        request.setAttribute("operate", 1);
        return "user_login";
    }

}

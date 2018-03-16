package com.suiyi.jpa.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.GoodCar;
import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.User;
import com.suiyi.jpa.service.GoodCarService;
import com.suiyi.jpa.service.GoodTypeService;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.UserService;

@Controller
@RequestMapping
public class GoodCarController {

    @Autowired
    private GoodCarService goodCarService;

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodTypeService goodTypeService;

    @RequestMapping(value = "/add_goodcar.do")
    public ModelAndView addGoodCar(Integer goodId, String userName, Integer amount, Integer pagenumber, String name,
                                   Integer goodtype, HttpServletRequest request) {
        Goods god = goodsService.findById(goodId);
        User user = userService.findByNickname(userName);
        if (god.getAmount() < amount) {
            throw new ExceptionMessage("超出库存");
        }
        List<GoodCar> car = goodCarService.findByGoodIdAndUserId(goodId, user.getId());
        GoodCar goodCar = null;
        if (car.size() != 0) {
            goodCar = car.get(0);
            goodCar.setAmount(amount + goodCar.getAmount());
            goodCarService.fillUpdate(goodCar, userName);
        } else {
            goodCar = new GoodCar();
            goodCar.setGoodId(goodId);
            goodCar.setUserId(user.getId());
            goodCar.setGoodId(goodId);
            goodCar.setAmount(amount);
            goodCarService.fillCreate(goodCar, userName);
        }
        goodCarService.save(goodCar);
        int totalcount = goodsService.findAll().size();
        int pagecount = 0;
        int pagesize = 10;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (pagenumber > pagecount || pagenumber < 0) {
            throw new ExceptionMessage("页数有错");
        }
        List<Goods> goods = null;
        if (name != null || goodtype != null) {
            goods = goodsService.findNameOrType(name, goodtype, pagenumber - 1, pagesize);
        } else {
            Page<Goods> good = goodsService.findList(pagenumber - 1, pagesize);
            goods = good.getContent();
        }
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("page", page);
        request.setAttribute("type", 3);
        request.setAttribute("userName", userName);
        request.setAttribute("name", name);
        request.setAttribute("goodtype", goodtype);
        List<GoodType> goodTypes = goodTypeService.findAll();
        request.setAttribute("goodType", goodTypes);
        return new ModelAndView("user_login", "goods", goods);
    }

    @RequestMapping(value = "/goodcar_list.do")
    public String goodCarList(Integer pagesize, Integer pagenumber, String userName, HttpServletRequest request) {
        int totalcount = goodCarService.allList().size();
        int pagecount = 0;
        int m = totalcount % pagesize;
        if (m > 0) {
            pagecount = totalcount / pagesize + 1;
        } else {
            pagecount = totalcount / pagesize;
        }
        if (pagenumber > pagecount || pagenumber < 0) {
            if (pagecount == 0) {
                request.setAttribute("goodCar", null);
            } else {
                throw new ExceptionMessage("页数有错");
            }
        } else {
            Page<GoodCar> goodCars = goodCarService.allPage(pagesize, pagenumber - 1);
            request.setAttribute("goodCar", goodCars.getContent());
        }
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("userName", userName);
        request.setAttribute("page", page);
        request.setAttribute("type", 2);
        return "user_login";
    }

    @RequestMapping(value = "/goodcar_delete.do")
    public String deleteGoodCar(Integer goodCarId, Integer pagesize, Integer pagenumber, String userName,
                                HttpServletRequest request) {
        goodCarService.delete(goodCarId);
        return "forward:goodcar_list.do?pagesize=" + pagesize + "&pagenumber=" + pagenumber;
    }

    @RequestMapping(value = "/addorplus_car.do")
    public String addOrPlusMount(Integer state, Integer id, Integer pagenumber, Integer pagecount, String userName, HttpServletRequest request) {
        GoodCar goodCar = goodCarService.detail(id);
        if (state == 0) {
            if (goodCar.getGoods().getAmount() > 0) {
                goodCar.setAmount(goodCar.getAmount() - 1);
            }
        }
        if (state == 1) {
            if (goodCar.getGoods().getAmount() > goodCar.getAmount()) {
                goodCar.setAmount(goodCar.getAmount() + 1);
            }
        }
        goodCarService.fillUpdate(goodCar, userName);
        goodCarService.save(goodCar);
        Page<GoodCar> goodCars = goodCarService.allPage(10, pagenumber - 1);
        request.setAttribute("goodCar", goodCars.getContent());
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("userName", userName);
        request.setAttribute("page", page);
        request.setAttribute("type", 2);
        request.setAttribute("goodCar", goodCars.getContent());
        return "user_login";
    }


    @RequestMapping(value = "/deleteAllCar.do")
    public String deleteAllCar(Integer operate, Integer[] ck1, Integer pagenumber, Integer pagecount, String userName,
                               HttpServletRequest request) {
        if (operate != null) {
            return "order_car_detail.do?userName=" + userName + "&ck1=" + ck1;
        }

        for (int i = 0; i < ck1.length; i++) {
            goodCarService.delete(ck1[i]);
        }
        Page<GoodCar> goodCars = goodCarService.allPage(10, pagenumber - 1);
        request.setAttribute("goodCar", goodCars.getContent());
        List<Integer> page = new LinkedList<>();
        page.add(pagenumber);
        page.add(pagecount);
        request.setAttribute("userName", userName);
        request.setAttribute("page", page);
        request.setAttribute("type", 2);
        request.setAttribute("goodCar", goodCars.getContent());
        return "user_login";

    }

}

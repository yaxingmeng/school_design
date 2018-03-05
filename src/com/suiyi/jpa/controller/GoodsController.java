package com.suiyi.jpa.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.Utils.EnumName;
import com.suiyi.jpa.Utils.ExceptionMessage;
import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.service.GoodTypeService;
import com.suiyi.jpa.service.GoodsService;

@Controller
@RequestMapping
public class GoodsController {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private GoodTypeService goodTypeService;
	
	@RequestMapping(value = "/good_list.do")
	public ModelAndView findAll(Integer type,String adminName,Integer pagesize,Integer pagenumber,HttpServletRequest request){
		int totalcount = goodsService.findAll().size();
		int pagecount = 0;
		int m = totalcount % pagesize;
		if (m > 0) {
			pagecount = totalcount / pagesize + 1;
		} else {
			pagecount = totalcount / pagesize;
		}
		if (pagenumber > pagecount || pagenumber < 0) {
			throw new ExceptionMessage("页数有错");
		}
		Page<Goods> good=goodsService.findList(pagenumber-1, pagesize);
		List<Goods> goods=good.getContent();
		List<Integer> page = new LinkedList<>();
		page.add(pagenumber);
		page.add(pagecount);
		request.setAttribute("page", page);
		request.setAttribute("adminName", adminName);
		if(type==0){
			return new ModelAndView("goodsAdmin","goods",goods);
		}
		return new ModelAndView("user_login","goods",goods);
	}
	
	@RequestMapping(value="/goodtype_list.do")
	public ModelAndView goodTypeList(HttpServletRequest request,String adminName,Integer type){
		List<GoodType> goodTypes=goodTypeService.findAll();
		request.setAttribute("adminName",adminName);
		request.setAttribute("type",type);
		return new ModelAndView("addGoods","goodType",goodTypes);
	}

	@RequestMapping(value="/add_good.do",method = RequestMethod.POST)
	public ModelAndView addGood(String name,String goodNo,Double price,Integer amount,Integer type,String adminName,HttpServletRequest request,
			HttpServletResponse response,MultipartFile image) throws IOException{
		String images=uploadFile(image, request);
		Goods good=new Goods();
        good.setGoodNo(goodNo);
        good.setName(name);
        good.setType(type);
        good.setPrice(price);
        good.setAmount(amount);
        good.setState(EnumName.GoodState.ON_SALE.getValue());
        good.setImage(images);
        goodsService.fillCreate(good, adminName);
		return null;
	        }  
	
	 //文件上传  
    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {  
        String fileName = file.getOriginalFilename();  
        String path=request.getSession().getServletContext().getRealPath("images/");  
        File tempFile = new File(path, new Date().getTime() + String.valueOf(fileName));  
        if (!tempFile.getParentFile().exists()) {  
            tempFile.getParentFile().mkdir();  
        }  
        if (!tempFile.exists()) {  
            tempFile.createNewFile();  
        }  
        file.transferTo(tempFile);  
        return "images/" + tempFile.getName();  
    }  
  
	      
}

package com.suiyi.jpa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.suiyi.jpa.bean.ExsistsException;
import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.bean.InOut;
import com.suiyi.jpa.bean.InStock;
import com.suiyi.jpa.bean.OutStock;
import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.bean.Stock;
import com.suiyi.jpa.service.GoodsService;
import com.suiyi.jpa.service.InOutService;
import com.suiyi.jpa.service.InService;
import com.suiyi.jpa.service.OutService;
import com.suiyi.jpa.service.ProvideService;
import com.suiyi.jpa.service.SaleService;
import com.suiyi.jpa.service.StockService;

@Controller
@RequestMapping
public class StockController {
	@Autowired
	private StockService stockService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private ProvideService provideService;
	@Autowired
	private InService inservice;
	@Autowired
	private SaleService saleservice;
	@Autowired
	private OutService outService;
	@Autowired
	private InOutService inoutService;
	/**
	 * 产看每个产品的库存
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/queryStock.do")
	public ModelAndView queryStock(Integer id) {
		System.out.println(id);
		Stock stock = stockService.getStockById(id);
		return new ModelAndView("detail", "stock", stock);
	}

	/**
	 * 添加库存信息和库存量
	 * 
	 * @param provideId
	 * @param goodsId
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = "/addStock.do")
	public ModelAndView addStock(Integer provideId, Integer goodsId, Integer amount) {
		Goods goods = goodsService.findById(goodsId);
		Provide provide = provideService.findById(provideId);
		if (goods == null || provide == null) {
			return new ModelAndView("error", "error", "商品或供应商不存在");
		}
		stockService.addStockAmount(provideId,goodsId,amount);
		return new ModelAndView("forward:getAll.do", null);

	}

	/**
	 * 销售商品减少库存
	 * @throws ExsistsException 
	 */
	@RequestMapping(value = "/outStock.do")
	@ResponseBody
	public String  outStock(Integer saleId, Integer goodsId, Integer amount) throws ExsistsException {
		Stock s = stockService.getStockById(goodsId);
		int a = s.getAmount();
		if (amount > a) {
			throw new ExsistsException("超出库存啦");
		}
		stockService.subStockAmount(saleId,goodsId, amount);
		return "出库成功";
	}

	/**
	 * 查询供应商的入库记录
	 */
	@RequestMapping(value = "/queryProvideStock.do")
	public String queryProvideStock(Model model,Integer pid) {
		List<InStock> instocks = inservice.findProvideStock(pid);
		model.addAttribute("instocks", instocks);
		return "provideStock";
	}

	/*
	 * 查询销售商的出库记录
	 * 
	 */
	@RequestMapping(value = "/querySaleStock.do")
	public ModelAndView querySaleStock(Integer sid) {
		List<OutStock> outStocks = outService.findSaleStock(sid);
		return new ModelAndView("saleStock", "outstocks", outStocks);

	}

	/**
	 * 查询商品的入库记录
	 */
	@RequestMapping(value = "/goodsInStock.do")
	public ModelAndView goodsInStock(Integer id) {
		List<InStock> instocks = inservice.findgoodsStock(id);
		return new ModelAndView("goodsInStock", "instocks", instocks);
	}

	/**
	 * 查询商品的出库记录
	 */
	@RequestMapping(value = "/goodsOutStock.do")
	public ModelAndView goodsOutStock(Integer id) {
		List<OutStock> outStocks = outService.findGoodsStock(id);
		return new ModelAndView("goodsOutStock", "outstocks", outStocks);
	}

	/**
	 * 查询某段时间内的入库信息
	 * 
	 * @throws Exception
	 
	@RequestMapping(value = "/timeInStock.do")
	public String timeInStock(Model model,String start, String end) throws Exception {
		Date start2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(start);
		Date end2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(end);
		Page<InStock> instocks = inservice.findByIntimeBetween(start2, end2);
		Page<OutStock> outStocks=outService.findByOuttimeBetween(start2, end2);
		model.addAttribute("instocks",instocks.getContent());
		model.addAttribute("outstocks",outStocks.getContent());
		return "timeInStock";
	}*/
	/**
	 * 查询某段时间内的出库信息
	 * 
	 * @throws Exception
	 
	@RequestMapping(value="/timeOutStock.do")
	public ModelAndView timeOutStock(String start, String end) throws Exception {
		Date start2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(start);
		Date end2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(end);
		List<OutStock> outstocks = outService.findByOuttimeBetween(start2, end2);
		return new ModelAndView("timeOutStock", "outstocks", outstocks);
	}
	*/
	
	@RequestMapping(value = "/timeInStock.do")
	public String timeInStock(Model model,@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date start, @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")Date end) throws Exception {
		//Date start2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(start);
		//Date end2 = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(end);
		List<InOut> stocks=inoutService.findAll(start, end);
		model.addAttribute("stocks",stocks);
		return "timeInStock";
		
	}
}

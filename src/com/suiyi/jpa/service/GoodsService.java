package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.repository.GoodsRepository;

@Service
public class GoodsService {
	@Autowired
	private GoodsRepository goodsRepository;
	/**
	 * 获取所有商品的信息
	 * @return
	 */
	public List<Goods> getAll()
	{
		return  (List<Goods>) goodsRepository.findAll();
	}
	/**
	 * 增加商品
	 */
	public void addGoods(Integer id,String name){
		Goods goods=new Goods();
		goods.setId(id);
		goods.setName(name);
		goodsRepository.save(goods);
	}
/**
 * 获取所需修改的商品信息
 */
	public Goods updateQuery(Integer id){
		return goodsRepository.findById(id);
	}
	/**
	 * 修改商品信息
	 */
	public void updateGoods(Integer id,String name){
		Goods goods=new Goods();
		goods.setId(id);
		goods.setName(name);
		goodsRepository.save(goods);
	}
}

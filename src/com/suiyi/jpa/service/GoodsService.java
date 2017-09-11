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
	public Goods addGoods(String name){
		Goods goods=new Goods();
		goods.setName(name);
		goods.setState(0);
		return goodsRepository.save(goods);
	}
/**
 * 获取所需修改的商品信息
 */
	public Goods updateQuery(Integer id){
		return goodsRepository.findOne(id);
				//findById(id);
	}
	/**
	 * 修改商品信息
	 */
	public void updateGoods(Integer id,String name){
		Goods goods=goodsRepository.findOne(id);
		goods.setId(id);
		goods.setName(name);
		goodsRepository.save(goods);
	}
	
	public Goods findById(Integer id)
	{
		return goodsRepository.findOne(id);
	}
	/**
	 * 修改商品状态
	 */
	public void changeState(Goods goods,Integer state){
		if(state==0){
			goods.setState(0);
		}else if(state==1){
		goods.setState(1);
		}
		goodsRepository.save(goods);
	}
	
	/**
	 * 根据商品名字寻找商品
	 */
	public Goods findByName(String name){
		return goodsRepository.findByName(name);
	}
	
	
	
}

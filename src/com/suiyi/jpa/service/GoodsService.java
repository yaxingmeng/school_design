package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Goods;
import com.suiyi.jpa.repository.GoodsRepository;

@Service
public class GoodsService extends BasicService<Goods, Integer> {
	
	@Autowired
	private GoodsRepository goodsRepository;
	
	public Page<Goods> findList(Integer pagenumber,Integer pagesize){
		Pageable pageable=new PageRequest(pagenumber, pagesize);
		return goodsRepository.findAll(pageable);
	}
	
	public List<Goods> findAll(){
		return goodsRepository.findAll();
	}
	
	public List<Goods> findByName(String name){
		return goodsRepository.findByNameContaining(name);
	}
	
	public List<Goods> findByType(Integer type){
		return goodsRepository.findByType(type);
	}
	
	public Goods findById(Integer id){
		return goodsRepository.findOne(id);
	}
	
	public Goods save(Goods goods){
		return goodsRepository.save(goods);
	}
	
}

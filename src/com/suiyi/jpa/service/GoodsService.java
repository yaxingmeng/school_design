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
	
	public List<Goods> findByNameContaining(String name){
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
	
	public List<Goods> findNameOrType(String name,Integer type,Integer pagenumber,Integer pagesize){
		Integer mount=pagesize*pagenumber;
		if(name!=null){
			if(type!=null){
				return goodsRepository.findByType(type, mount);
			}
			return goodsRepository.FindByName(name, mount);
		}
		return goodsRepository.findByNameAndType(name, type, mount);
	}
	
	public boolean findByNameOrNO(String name,String no){
		if(goodsRepository.findByName(name).size()!=0){
			return false;
		}else if(goodsRepository.findByGoodNo(no).size()!=0){
			return false;
		}else{
			return true;
		}
		
	}
	
}

package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.GoodType;
import com.suiyi.jpa.repository.GoodTypeRepository;

@Service
public class GoodTypeService extends BasicService<GoodType, Integer>{
	
	@Autowired
	private GoodTypeRepository goodTypeRepository;
	
	public List<GoodType> findAll(){
		return goodTypeRepository.findAll();
	}
	
	public Page<GoodType> findList(Integer pagenumber,Integer pagesize){
		Pageable pageable=new PageRequest(pagenumber, pagesize);
		return goodTypeRepository.findAll(pageable);
	}
	
	public GoodType detail(Integer id){
		return goodTypeRepository.findOne(id);
	}
	
	public GoodType findByName(String name){
		return goodTypeRepository.findByNameContaining(name);
	}
	
	public GoodType save(GoodType goodType){
		return goodTypeRepository.save(goodType);
	}
	
	public GoodType changeState(Integer id,Integer state,String operator){
		GoodType goodType=detail(id);
		goodType.setState(state);
		fillUpdate(goodType, operator);
		return save(goodType);
	}

}

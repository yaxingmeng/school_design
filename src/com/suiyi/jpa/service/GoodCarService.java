package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.GoodCar;
import com.suiyi.jpa.repository.GoodCarRepository;

@Service
public class GoodCarService extends BasicService<GoodCar,Integer> {
	
	@Autowired
	private GoodCarRepository goodCarRepository;
	
	public GoodCar save(GoodCar goodCar){
		return goodCarRepository.save(goodCar);
	}

	public List<GoodCar> allList(){
		return (List<GoodCar>) goodCarRepository.findAll();
	}
	
	public Page<GoodCar> allPage(int pagesize,Integer pagenumber){
		Pageable pageable=new PageRequest(pagenumber, pagesize);
		return goodCarRepository.findAll(pageable);
	}
	
	public List<GoodCar> findByGoodIdAndUserId(Integer goodId,Integer userId){
		return goodCarRepository.findByGoodIdAndUserId(goodId, userId);
	}
	
	public void delete(Integer id){
		goodCarRepository.delete(id);
	}
	
	public GoodCar detail(Integer id){
		return goodCarRepository.findOne(id);
	}
}

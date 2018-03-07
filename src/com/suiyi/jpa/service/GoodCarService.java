package com.suiyi.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}

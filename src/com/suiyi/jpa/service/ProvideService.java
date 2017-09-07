package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.repository.ProvideRepository;

@Service
public class ProvideService {
	@Autowired
	private ProvideRepository provideRepository;

	public List<Provide> findAll() {
		return provideRepository.findAll();
	}

	public void addProvide(String name) {
		Provide p = new Provide();
		p.setName(name);
		provideRepository.save(p);
	}
	
	public Provide findById(Integer id){
		return provideRepository.findById(id);
	}
	
	public void updateProvide(Provide p){
		
		provideRepository.save(p);
	}
	
	public void deleteProvide(Integer id){
		Provide p=provideRepository.findById(id);
		provideRepository.delete(p);
	}
	
}

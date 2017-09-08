package com.suiyi.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.Provide;
import com.suiyi.jpa.bean.Sale;
import com.suiyi.jpa.repository.SaleRepository;

@Service
public class SaleService {
	@Autowired
	private SaleRepository saleRepository;

	public List<Sale> findAll() {
		return saleRepository.findAll();
	}

	public Sale findById(Integer id) {
		return saleRepository.findById(id);
	}

	public void addSale(String name) {
		Sale s = new Sale();
		s.setName(name);
		s.setState(1);
		saleRepository.save(s);
	}

	public void updateSale(Integer id,String name) {
		Sale s=saleRepository.findById(id);
		s.setName(name);
		saleRepository.save(s);
	}

	public void deleteSale(Integer id,Integer state) {
		Sale s = saleRepository.findById(id);
		if(state==0){
			s.setState(0);
		}else if(state==1){
			s.setState(1);
		}
		saleRepository.save(s);
	}
}

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
		saleRepository.save(s);
	}

	public void updateSale(Sale s) {
		
		saleRepository.save(s);
	}

	public void deleteSale(Integer id) {
		Sale s = saleRepository.findById(id);
		saleRepository.delete(s);
	}
}

package com.suiyi.jpa.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suiyi.jpa.bean.InOut;
import com.suiyi.jpa.repository.InOutRepository;

@Service
public class InOutService {
@Autowired
private InOutRepository inOutRepository;
public List<InOut> findAll(Date start,Date end){
	return inOutRepository.findAllBetweenOrderByTime(start, end);
}
}

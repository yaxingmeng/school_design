package com.suiyi.jpa.Utils;

import static org.junit.Assert.fail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.suiyi.jpa.bean.GoodCar;
import com.suiyi.jpa.service.GoodCarService;

public class Test {

	@Autowired
	private GoodCarService goodCarService;
	
	@org.junit.Test
	public void test() {
		fail("Not yet implemented");
	}

	@org.junit.Test
	public void testGoodCar(){
		Page<GoodCar> cars=goodCarService.allPage(10, 0);
		System.out.println(cars.getContent());
	}
}

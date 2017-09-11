package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.Stocks;

public interface StacksRepository extends CrudRepository<Stocks, Integer> {
	/**
	 * 查询商品的出入库信息
	 */
	@Query(value="select i.id as id ,g.name as goods,p.name as provide,i.amount as inamount,i.intime"
			+ ",s.name as sale, o.amount as outamount,o.outtime from instock i, goods g,"
			+ " provide p cross join outstock o, sale s where i.goods_id=g.id and i.provide_id=p.id "
			+ "  and o.sale_id=s.id and i.goods_id=o.goods_id and "
			+ "( i.intime between ?1 and ?2 ) and (o.outtime between ?3 and  ?4) group by i.id"
			+ " order by i.intime,o.outtime DESC",nativeQuery=true)

	List<Stocks> findAllStocks(Date start1, Date end1, Date start2, Date end2);
}

package com.suiyi.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.suiyi.jpa.bean.InOut;

public interface InOutRepository extends CrudRepository<InOut, Integer> {
	@Query(value="select * from inoutstock where time between ?1 and ?2 order by time desc",nativeQuery=true)
	List<InOut> findAllBetweenOrderByTime(Date start, Date end);
}
